package com.selfcabinet.controller;

import com.selfcabinet.model.Cabinet;
import com.selfcabinet.service.CabinetService;
import com.selfcabinet.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;
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

    @ApiOperation(value = "凭二维码开柜")
    @RequestMapping(path = "open",method = RequestMethod.PATCH)
    public Cabinet OpenCabinetByQRCode(@RequestParam (value = "QRContent")String QRContent)throws Exception{
        return cabinetService.OpenCabinetByQRCode(QRContent);
    }

    @ApiOperation(value = "关闭柜子")
    @RequestMapping(path = "close",method = RequestMethod.PATCH)
    public void closeCabinetByQRCode(@RequestParam(value = "QRContent")String QRContent)throws Exception{
        cabinetService.closeCabinetByQRCode(QRContent);
    }

    @ApiOperation(value = "用户凭取货码打开柜子")
    @RequestMapping(path = "openByCarrierCode",method = RequestMethod.PATCH)
    public Cabinet OpenCabinetByCarrierCode(@RequestParam (value = "carrier_code")String carrier_code)throws Exception{
        return cabinetService.openCabinetByCarrierCode(carrier_code);
    }

    @ApiOperation(value = "用户凭取货码关闭柜子")
    @RequestMapping(path = "closeByCarrierCode",method = RequestMethod.PATCH)
    public void closeCabinetByCarrierCode(@RequestParam(value = "carrier_code")String carrier_code)throws Exception{
        cabinetService.closeCabinetByCarrierCode(carrier_code);
    }

}
