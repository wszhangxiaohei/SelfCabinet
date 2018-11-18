package com.selfcabinet.service;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.CabinetMapper;
import com.selfcabinet.mapper.CupboardMapper;
import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.Cupboard;
import com.selfcabinet.model.Order;
import com.selfcabinet.model.SelfCabinetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final CupboardMapper cupboardMapper;
    private final CabinetMapper cabinetMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper,CupboardMapper cupboardMapper,CabinetMapper cabinetMapper){
        this.orderMapper=orderMapper;
        this.cupboardMapper=cupboardMapper;
        this.cabinetMapper=cabinetMapper;
    }

//    //新的订单信息来，分配自提柜
//    public Cabinet distribution(Order order)throws Exception {
//        //订单中至少有order_id user_id courier_id bar_id
//        //Cabinet cabinet =new Cabinet();
//        if (cupboardMapper.getSpareNum(order.getBar_id())!=0){
//            //有空余座位
//            List<Cupboard> listCupBoard= cupboardMapper.getByBarId(order.getBar_id());
//            OUT:
//            for (int i=0;i<listCupBoard.size();i++){
//                if (listCupBoard.get(i).getSpare_num()!=0){
//                    //找到cupboard
//                    cupboardMapper.updateSpareNumbyId(listCupBoard.get(i).getSpare_num()-1,listCupBoard.get(i).getCupboard_id());
//                    List<Cabinet> listCabinet = cabinetMapper.getByCupboardId(listCupBoard.get(i).getCupboard_id());
//                    for (int j=0; j<listCabinet.size();j++) {
//                        if (listCabinet.get(j).getUsed().equals(Cabinet.SPARE) && listCabinet.get(j).getState().equals(Cabinet.NORMAL)) {
//                            //找到cabinet
//                            cabinetMapper.updateUsedById(Cabinet.ORDER,listCabinet.get(j).getCabinet_id());
//                            Cabinet cabinet = new Cabinet(listCabinet.get(j));
//                            cabinet.setUsed(Cabinet.ORDER);
//                            //更新order库
//                            order.setCabinet_id(listCabinet.get(j).getCabinet_id());
//                            order.setCupboard_id(listCabinet.get(j).getCupboard_id());
//                            order.setState(Order.UNDONE);
//                            orderMapper.insert(order);
//                            return cabinet;
//                        }
//                    }
//                }
//
//            }
//            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR_CABINET_DISTRIBUTION, ResponseMessage.ERROR_CABINET_DISTRIBUTION);
//        }
//        else{
//            //没有空余座位
//            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.NO_SPARE_CABINET, ResponseMessage.NO_SPARE_CABINET);
//        }
//    }

    public String cancelOrder(String order_id)throws Exception{
        Order order=orderMapper.getById(order_id);
        Cabinet cabinet=cabinetMapper.getById(order.getCabinet_id());
        if (cabinet.getUsed().equals(Cabinet.USED)){
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.FAILED_CANCEl_ORDER, ResponseMessage.FAILED_CANCEl_ORDER);

        }
        else if (cabinet.getUsed().equals(Cabinet.ORDER)){
            cabinetMapper.updateUsedById(Cabinet.SPARE,cabinet.getCabinet_id());
            cupboardMapper.updateSpareNumbyId(cupboardMapper.getSpareNumByCupboardId(order.getCupboard_id())+1,order.getCupboard_id());
            orderMapper.updateStateById(Order.DONE,order_id);
            return ResponseMessage.SUCCESS_CANCEL_ORDER;
        }
        else{
            throw new SelfCabinetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseMessage.ERROR_CABINET_USED, ResponseMessage.ERROR_CABINET_USED);
        }

    }



}
