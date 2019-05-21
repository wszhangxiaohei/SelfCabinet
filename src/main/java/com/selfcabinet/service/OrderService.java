package com.selfcabinet.service;

import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.CabinetMapper;
import com.selfcabinet.mapper.CupboardMapper;
import com.selfcabinet.mapper.OrderMapper;
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
    private static final Integer QRExpireTime = 1000 * 60 * 60*24*2;


    @Autowired
    public OrderService(OrderMapper orderMapper, CupboardMapper cupboardMapper, CabinetMapper cabinetMapper){
        this.orderMapper=orderMapper;
        this.cupboardMapper=cupboardMapper;
        this.cabinetMapper=cabinetMapper;
    }



    public String getOneOrder(String order_id) throws  Exception{

        Order order = orderMapper.getById(order_id);

        String contentInfo=order_id+"_"+order.getCupboard_id()+"_"+"courier"+"_"+order.getCarrier_code();
        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        String content= CommonUtil.createJWT(contentInfo,createTime, expireTime);

        return content;

    }


    public String makeOrder() throws  Exception{

        String  cupboard_id="";
        //检查剩余数量
        List<Cupboard> cupboards=cupboardMapper.getAll();
        for (int i=0; i<cupboards.size();i++) {
            if (cupboards.get(i).getSpare_num()!=0){
                cupboard_id=cupboards.get(i).getCupboard_id();
                cupboardMapper.updateSpareNumbyId(cupboards.get(i).getSpare_num()-1,cupboard_id);
                break;
            }
        }
        if (cupboard_id.equals("")){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.NO_SPARE_CABINET);
        }

        Order order=new Order();

        String type="user";

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
        order.setOrder_id(order_id);
        order.setCupboard_id(cupboard_id);
        order.setCabinet_id("0");
        order.setCarrier_code(carrier_code);
        order.setState(Order.UNDONE);
        orderMapper.insert(order);

        String contentInfo=order_id+"_"+cupboard_id+"_"+type+"_"+carrier_code;

        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        String content= CommonUtil.createJWT(contentInfo,createTime, expireTime);


        return carrier_code+"_"+content;
    }

}
