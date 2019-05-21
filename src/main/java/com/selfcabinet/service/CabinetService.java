package com.selfcabinet.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.*;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.Order;
import com.selfcabinet.model.SelfCabinetException;
import com.selfcabinet.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabinetService {
    private final CabinetMapper cabinetMapper;
    private final OrderMapper orderMapper;
    private final CupboardMapper cupboardMapper;
    private final QRCodeService qrCodeService;

    private static final Logger log = LoggerFactory.getLogger(CabinetService.class);

    @Autowired
    public CabinetService(CabinetMapper cabinetMapper, OrderMapper orderMapper, CupboardMapper cupboardMapper, QRCodeService qrCodeService){
        this.cabinetMapper=cabinetMapper;
        this.orderMapper=orderMapper;
        this.cupboardMapper=cupboardMapper;
        this.qrCodeService=qrCodeService;
    }

    public Cabinet OpenCabinetByQRCode(String QRContent) throws Exception {

        DecodedJWT jwt = CommonUtil.phraseJWT(QRContent);
        String[] content=jwt.getSubject().split("_");


        //String[] content=QRContent.split("_");
        String order_id=content[0];
        String cupboard_id=content[1];
        String type=content[2];
        String carrier_code=content[3];
        Order order= new Order();
        Cabinet cabinet=new Cabinet();

        if (type.equals("courier")) {
            //已经送过货
            String cabinet_id = orderMapper.getById(order_id).getCabinet_id();
            if (cabinetMapper.getById(cabinet_id) != null) {
                throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.MULT_SCAN_CODE_FOR_CURRIER);
            }

           //分配柜子
            List<Cabinet> listCabinet = cabinetMapper.getByCupboardId(cupboard_id);
            for (int i=0; i<listCabinet.size();i++) {
                if (listCabinet.get(i).getUsed().equals(Cabinet.SPARE) && listCabinet.get(i).getState().equals(Cabinet.NORMAL)) {
                    //找到cabinet
                    cabinet.setCabinet(listCabinet.get(i));
                    cabinet.setOpen(Cabinet.OPEN);
                    cabinetMapper.updateOpenById(Cabinet.OPEN,cabinet.getCabinet_id());

                    //更新order库
                    orderMapper.updateCabinetIdById(cabinet.getCabinet_id(), order_id);
                    orderMapper.updateBarIdById(cabinet.getBar_id(), order_id);

                    log.info("[cabinet][open]管理员送货打开柜门"+order_id);

                    return cabinet;
                }
            }
       }
       else if (type.equals("user")){

           if (orderMapper.getIdNumById(order_id)!=1){
               throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.MULT_ORDER);
           }
           //用户
           order=orderMapper.getById(order_id);
           if (order.getCabinet_id().equals("0")){
               throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.NO_GOODS);
           }
           else if (order.getState().equals(Order.DONE)){
               throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.MULT_SCAN_CODE_FOR_USER);
           }
           cabinet.setCabinet(cabinetMapper.getById(order.getCabinet_id()));

           if (cabinet.getState().equals(Cabinet.NORMAL)){
               if (cabinet.getUsed().equals(Cabinet.SPARE)){
                   //提示商品未送到
                   throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.NO_GOODS);
               }
               else if (cabinet.getUsed().equals(Cabinet.USED)){
                   log.info("[cabinet][open]用户打开柜子"+order_id);
                   cabinetMapper.updateOpenById(Cabinet.OPEN,cabinet.getCabinet_id());
                   cabinet.setOpen(Cabinet.OPEN);
                   return cabinet;
               }
           }
           else {
               //柜子硬件故障
               throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_HARD_STATE_OF_CABINET);
           }
       }

        //二维码类型出错
        throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_QRCODE_TYPE);
    }

    public Cabinet openCabinetByCarrierCode(String carrier_code) throws Exception{


        int  num = orderMapper.getIdNumByCarrierCode(carrier_code);
        log.info("[cabinet][openCabinetByCarrierCode]取货码数量"+num);

        if (orderMapper.getIdNumByCarrierCode(carrier_code)!=1){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.MULT_ORDER);
        }

        Order order= new Order();
        Cabinet cabinet=new Cabinet();
        order=orderMapper.getByCarrierCode(carrier_code);
        log.info("[cabinet][openCabinetByCarrierCode]cabinet_id "+order.getCabinet_id());
        if (order.getCabinet_id().equals("0")){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.NO_GOODS);
        }
        else if (order.getState().equals(Order.DONE)){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.MULT_SCAN_CODE_FOR_USER);
        }
        cabinet.setCabinet(cabinetMapper.getById(order.getCabinet_id()));

        if (cabinet.getState().equals(Cabinet.NORMAL)){
            if (cabinet.getUsed().equals(Cabinet.SPARE)){
                //提示商品未送到
                throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.NO_GOODS);
            }
            else if (cabinet.getUsed().equals(Cabinet.USED)){
                cabinetMapper.updateOpenById(Cabinet.OPEN,cabinet.getCabinet_id());
                cabinet.setOpen(Cabinet.OPEN);
                log.info("[cabinet][openCabinetByCarrierCode]用户打开柜子"+carrier_code);
                return cabinet;
            }
        }

        //柜子硬件故障
        throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_HARD_STATE_OF_CABINET);

    }

    public void closeCabinetByCarrierCode(String carrier_code){

        if (orderMapper.getIdNumByCarrierCode(carrier_code)==0){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.NO_GOODS);
        }

        if (orderMapper.getIdNumByCarrierCode(carrier_code)!=1){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.MULT_ORDER);
        }

        Order order=orderMapper.getByCarrierCode(carrier_code);
        if (order==null){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_ORDER_ID);
        }

        //1、更新订单表
        orderMapper.updateStateById(Order.DONE,order.getOrder_id());
        //2、更新cupboard表
        cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(order.getCupboard_id())+1,order.getCupboard_id());
        //3、更新cabinet表
        cabinetMapper.updateUsedById(Cabinet.SPARE,order.getCabinet_id());

        log.info("[cabinet][closeCabinetByCarrierCode]用户关闭柜子"+carrier_code);

    }

    public void closeCabinetByQRCode(String QRContent) throws Exception {
        DecodedJWT jwt = CommonUtil.phraseJWT(QRContent);
        String[] content=jwt.getSubject().split("_");
        String order_id=content[0];
        String cupboard_id=content[1];
        String type=content[2];

        if (orderMapper.getIdNumById(order_id)!=1){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ResponseMessage.ERROR,ResponseMessage.MULT_ORDER);
        }
        Order order = orderMapper.getById(order_id);
        if (order==null){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_ORDER_ID);
        }

        if (type.equals("courier")){

            //更新cabinet表
            cabinetMapper.updateUsedById(Cabinet.USED,order.getCabinet_id());
            log.info("[cabinet][closeCabinetByQRCode]管理员关闭柜子"+order_id);

        }
        else if (type.equals("user")){
            //order = orderMapper.getById(order_id);
            //1、更新订单表
            orderMapper.updateStateById(Order.DONE,order_id);
            //2、更新cupboard表
            cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(order.getCupboard_id())+1,order.getCupboard_id());
            //3、更新cabinet表
            cabinetMapper.updateUsedById(Cabinet.SPARE,order.getCabinet_id());

            log.info("[cabinet][closeCabinetByQRCode]用户关闭柜子"+order_id);

        }
        else {
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_QRCODE_TYPE);
        }
    }

    public Cabinet showState(String cabinet_id){
        return cabinetMapper.getById(cabinet_id);
    }

    public List<Cabinet> showStateByCupBoard(String cupboard_id){
        return cabinetMapper.getByCupboardId(cupboard_id);
    }

    public Cabinet changeOpen(String open, String cabinet_id){
        if (open.equals("open")){
            cabinetMapper.updateOpenById(Cabinet.OPEN,cabinet_id);
            return cabinetMapper.getById(cabinet_id);
        }
        else if (open.equals("close")){
            cabinetMapper.updateOpenById(Cabinet.CLOSED,cabinet_id);
            return cabinetMapper.getById(cabinet_id);
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_CABINET_OPEN);
        }
    }


}
