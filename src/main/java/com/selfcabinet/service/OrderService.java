package com.selfcabinet.service;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.CabinetMapper;
import com.selfcabinet.mapper.CupboardMapper;
import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.mapper.StimulateOrderMapper;
import com.selfcabinet.model.*;
import com.selfcabinet.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final CupboardMapper cupboardMapper;
    private final CabinetMapper cabinetMapper;
    private final StimulateOrderMapper stimulateOrderMapper;
    private static final Integer QRExpireTime = 1000 * 60 * 60*24*2;


    @Autowired
    public OrderService(OrderMapper orderMapper, CupboardMapper cupboardMapper, CabinetMapper cabinetMapper, StimulateOrderMapper stimulateOrderMapper){
        this.orderMapper=orderMapper;
        this.cupboardMapper=cupboardMapper;
        this.cabinetMapper=cabinetMapper;
        this.stimulateOrderMapper=stimulateOrderMapper;
    }



    public String getOneOrder(String order_id) throws  Exception{
        StimulateOrder stimulateOrder = stimulateOrderMapper.getByOrderId(order_id);

        String contentInfo=order_id+"_"+stimulateOrder.getCupboard_id()+"_"+"courier"+"_"+stimulateOrder.getCarrier_code();
        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        String content= CommonUtil.createJWT(contentInfo,createTime, expireTime);

        return content;

    }


    public String makeOrder() throws  Exception{

        StimulateOrder stimulateOrder=new StimulateOrder();
        String  cupboard_id="10000";
        String type="user";
        String status="0";

        String order_id="";
        Random random = new Random();
        for (int i = 0; i < 10; i++){
            order_id += String.valueOf(random.nextInt(10));
        }

        String carrier_code="";
        Random random2 = new Random();
        for (int i = 0; i < 6; i++){
            carrier_code += String.valueOf(random2.nextInt(10));
        }
        stimulateOrder.setOrder_id(order_id);
        stimulateOrder.setCupboard_id(cupboard_id);
        stimulateOrder.setType(type);
        stimulateOrder.setCarrier_code(carrier_code);
        stimulateOrder.setStatus(status);
        stimulateOrderMapper.insert(stimulateOrder);

        String contentInfo=order_id+"_"+cupboard_id+"_"+type+"_"+carrier_code;

        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        String content= CommonUtil.createJWT(contentInfo,createTime, expireTime);


        return carrier_code+"_"+content;
    }

}
