package com.selfcabinet.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CabinetService {
    private final CabinetMapper cabinetMapper;
    private final OrderMapper orderMapper;
    private final CupboardMapper cupboardMapper;

    @Autowired
    public CabinetService(CabinetMapper cabinetMapper,OrderMapper orderMapper,CupboardMapper cupboardMapper){
        this.cabinetMapper=cabinetMapper;
        this.orderMapper=orderMapper;
        this.cupboardMapper=cupboardMapper;
    }

    public Cabinet OpenCabinetByQRCode(String QRContent){
        String[] content=QRContent.split("_");
        String order_id=content[0];
        Order order= orderMapper.getById(order_id);
        if (order==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_ORDER_ID);
        }
        Cabinet cabinet=cabinetMapper.getById(order.getCabinet_id());
        if (cabinet.getState().equals(Cabinet.NORMAL)){
            if(content[1].equals("courier")){
                //快递员
                if (cabinet.getUsed().equals(Cabinet.ORDER)){
                    cabinetMapper.updateUsedById(Cabinet.USED,cabinet.getCabinet_id());
                    cabinet.setUsed(Cabinet.USED);
                    cabinet.setOpen(Cabinet.OPEN);
                    return cabinet;
                }
            }
            else if (content[1].equals("user")){
                //用户
                if (cabinet.getUsed().equals(Cabinet.ORDER)){
                    //提示商品未送到
                    throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.NO_GOODS);
                }
                else if (cabinet.getUsed().equals(Cabinet.USED)){
                    cabinet.setOpen(Cabinet.OPEN);
                    return cabinet;
                }
            }
            else {
                //二维码类型出错
                throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_QRCODE_TYPE);
            }
        }
        //柜子故障
        throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_HARD_STATE_OF_CABINET);

    }

    public void afterUsed(String order_id){
        Order order = orderMapper.getById(order_id);
        //1、更新订单表
        orderMapper.updateStateById(Order.DONE,order_id);
        //2、更新cupboard表
        cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(order.getCupboard_id())+1,order.getCupboard_id());
        //3、更新cabinet表
        cabinetMapper.updateUsedById(Cabinet.SPARE,order.getCabinet_id());
    }




}
