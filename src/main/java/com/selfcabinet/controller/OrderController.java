package com.selfcabinet.controller;

import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.Order;
import com.selfcabinet.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/order")
@RestController
@EnableAutoConfiguration
@Api(tags = "Order", description = "订单相关操作")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @ApiModelProperty(value = "新订单来分配自提柜")
    @RequestMapping(path = "distribution",method = RequestMethod.POST)
    public Cabinet distribution(@RequestBody Order order)throws Exception{
        return orderService.distribution(order);
    }

    @ApiModelProperty(value = "取消订单")
    @RequestMapping(path = "cancelOrder",method = RequestMethod.PATCH)
    public String cancel(@RequestParam(value = "order_id")String order_id)throws Exception{
        return orderService.cancelOrder(order_id);
    }
}
