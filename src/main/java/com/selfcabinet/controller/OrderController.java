package com.selfcabinet.controller;

import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.mapper.StimulateOrderMapper;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.Order;
import com.selfcabinet.model.StimulateOrder;
import com.selfcabinet.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/order")
@RestController
@EnableAutoConfiguration
@Api(tags = "Order", description = "订单相关操作")
public class OrderController {
    private final OrderService orderService;
    private final StimulateOrderMapper stimulateOrderMapper;

    @Autowired
    public OrderController(OrderService orderService , StimulateOrderMapper stimulateOrderMapper){
        this.orderService=orderService;
        this.stimulateOrderMapper=stimulateOrderMapper;
    }

    @ApiOperation(value = "获取所有未完成订单(快递员显示)")
    @RequestMapping(path ="getAllUndoneOrder",method = RequestMethod.GET)
    public List<StimulateOrder> getUndoneOrder(){
        return stimulateOrderMapper.getUndoneOrder();
    }

    @ApiOperation(value = "模拟下单(用户下单)")
    @RequestMapping(path = "makeOrder" ,method = RequestMethod.POST)
    public String makeOrder() throws Exception{
        return orderService.makeOrder();
    }

    @ApiOperation(value = "获取未完成订单(快递员显示)")
    @RequestMapping(path ="getUndoneOrder",method = RequestMethod.GET)
    public String getOneOrder(@RequestParam(value = "order_id") String order_id) throws Exception{
        return orderService.getOneOrder(order_id);
    }


//    @ApiModelProperty(value = "新订单来分配自提柜")
//    @RequestMapping(path = "distribution",method = RequestMethod.POST)
//    public Cabinet distribution(@RequestBody Order order)throws Exception{
//        return orderService.distribution(order);
//    }

//    @ApiModelProperty(value = "取消订单")
//    @RequestMapping(path = "cancelOrder",method = RequestMethod.PATCH)
//    public String cancel(@RequestParam(value = "order_id")String order_id)throws Exception{
//        return orderService.cancelOrder(order_id);
//    }
}
