package com.selfcabinet.controller;

import com.selfcabinet.model.Cabinet;
import com.selfcabinet.service.CabinetService;
import com.selfcabinet.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;

@RequestMapping(path = "/cabinet")
@RestController
@EnableAutoConfiguration
@Api(tags = "Cabinet", description = "自提柜相关操作")
public class CabinetController {
    private final CabinetService cabinetService;

    @Autowired
    public CabinetController(CabinetService cabinetService,OrderService orderService){
        this.cabinetService=cabinetService;
    }

    @ApiModelProperty(value = "凭二维码开柜")
    @RequestMapping(path = "open")
    public Cabinet OpenCabinetByQRCode(@RequestParam (value = "QRContent")String QRContent)throws Exception{
        return cabinetService.OpenCabinetByQRCode(QRContent);
    }

    @ApiModelProperty(value = "用户取件后重重柜子状态")
    @RequestMapping(path = "reset",method = RequestMethod.PATCH)
    public void reset(@RequestParam(value = "order_id")String order_id){
        cabinetService.afterUsed(order_id);
    }


}
