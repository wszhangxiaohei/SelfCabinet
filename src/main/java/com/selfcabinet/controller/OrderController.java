package com.selfcabinet.controller;

import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.model.Order;
import com.selfcabinet.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/order")
@RestController
@EnableAutoConfiguration
@Api(tags = "Order", description = "模拟订单相关操作（面向演示显示相关订单使用）")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService , OrderMapper orderMapper){
        this.orderService=orderService;
        this.orderMapper=orderMapper;
    }

    @ApiOperation(value = "获取所有未完成订单(快递员显示)")
    @RequestMapping(path ="getAllUndoneOrder",method = RequestMethod.GET)
    public List<Order> getUndoneOrder(){
        return orderMapper.getUndoneOrder();
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

}
