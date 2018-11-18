package com.selfcabinet.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.CabinetMapper;
import com.selfcabinet.mapper.CupboardMapper;
import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.Order;
import com.selfcabinet.model.SelfCabinetException;
import com.selfcabinet.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheAnnotationParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CabinetService {
    private final CabinetMapper cabinetMapper;
    private final OrderMapper orderMapper;
    private final CupboardMapper cupboardMapper;
    private final QRCodeService qrCodeService;

    @Autowired
    public CabinetService(CabinetMapper cabinetMapper,OrderMapper orderMapper,CupboardMapper cupboardMapper,QRCodeService qrCodeService){
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
        Order order= new Order();
        Cabinet cabinet=new Cabinet();


       if (type.equals("courier")){
           //分配柜子
           if (cupboardMapper.getSpareNumByCupboardId(cupboard_id)!=0){
               List<Cabinet> listCabinet = cabinetMapper.getByCupboardId(cupboard_id);
               for (int i=0; i<listCabinet.size();i++) {
                   if (listCabinet.get(i).getUsed().equals(Cabinet.SPARE) && listCabinet.get(i).getState().equals(Cabinet.NORMAL)) {
                       //找到cabinet
                       cabinet.setCabinet(listCabinet.get(i));
                       cabinet.setUsed(Cabinet.USED);
                       cabinet.setOpen(Cabinet.OPEN);
                       cabinetMapper.updateUsedById(Cabinet.USED,cabinet.getCabinet_id());
                       cabinetMapper.updateOpenById(Cabinet.OPEN,cabinet.getCabinet_id());

                       //更新cupboard库
                       cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(cupboard_id)-1,cupboard_id);

                       //更新order库
                       order.setOrder_id(order_id);
                       order.setCupboard_id(cupboard_id);
                       order.setBar_id(cabinet.getBar_id());
                       order.setCabinet_id(cabinet.getCabinet_id());
                       order.setState(Order.UNDONE);
                       orderMapper.insert(order);
                       return cabinet;
                   }
               }


           }
           else {
               throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.NO_SPARE_CABINET, ResponseMessage.NO_SPARE_CABINET);
           }
       }
       else if (type.equals("user")){
           //用户
           order=orderMapper.getById(order_id);
           if (order==null){
                throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_ORDER_ID, ResponseMessage.ERROR_ORDER_ID);
           }
           cabinet.setCabinet(cabinetMapper.getById(order.getCabinet_id()));

           if (cabinet.getState().equals(Cabinet.NORMAL)){
               if (cabinet.getUsed().equals(Cabinet.SPARE)){
                   //提示商品未送到
                   throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.NO_GOODS, ResponseMessage.NO_GOODS);
               }
               else if (cabinet.getUsed().equals(Cabinet.USED)){
                   cabinetMapper.updateOpenById(Cabinet.OPEN,cabinet.getCabinet_id());
                   cabinet.setOpen(Cabinet.OPEN);

                   //1、更新订单表
                   orderMapper.updateStateById(Order.DONE,order_id);
                   //2、更新cupboard表
                   cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(order.getCupboard_id())+1,order.getCupboard_id());
                   //3、更新cabinet表
                   cabinetMapper.updateUsedById(Cabinet.SPARE,order.getCabinet_id());

                   return cabinet;
               }
           }
           else {
               //柜子硬件故障
               throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_HARD_STATE_OF_CABINET, ResponseMessage.ERROR_HARD_STATE_OF_CABINET);
           }
       }

        //二维码类型出错
        throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR_QRCODE_TYPE, ResponseMessage.ERROR_QRCODE_TYPE);
    }

    public void afterUsed(String QRContent) throws Exception {
        DecodedJWT jwt = CommonUtil.phraseJWT(QRContent);
        String[] content=jwt.getSubject().split("_");
        String order_id=content[0];
        Order order = orderMapper.getById(order_id);
        //1、更新订单表
        orderMapper.updateStateById(Order.DONE,order_id);
        //2、更新cupboard表
        cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(order.getCupboard_id())+1,order.getCupboard_id());
        //3、更新cabinet表
        cabinetMapper.updateUsedById(Cabinet.SPARE,order.getCabinet_id());
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
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_CABINET_OPEN, ResponseMessage.ERROR_CABINET_OPEN);
        }
    }


}
