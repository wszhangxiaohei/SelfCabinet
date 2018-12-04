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

    @ApiModelProperty(value = "凭二维码开柜")
    @RequestMapping(path = "open",method = RequestMethod.PATCH)
    public Cabinet OpenCabinetByQRCode(@RequestParam (value = "QRContent")String QRContent)throws Exception{
        return cabinetService.OpenCabinetByQRCode(QRContent);
    }

    @ApiModelProperty(value = "关闭柜子")
    @RequestMapping(path = "close",method = RequestMethod.PATCH)
    public void closeCabinetByQRCode(@RequestParam(value = "QRContent")String QRContent)throws Exception{
        cabinetService.closeCabinetByQRCode(QRContent);
    }

    @ApiModelProperty(value = "凭取货码开柜")
    @RequestMapping(path = "openByCarrierCode",method = RequestMethod.PATCH)
    public Cabinet OpenCabinetByCarrierCode(@RequestParam (value = "carrier_code")String carrier_code)throws Exception{
        return cabinetService.openCabinetByCarrierCode(carrier_code);
    }

    @ApiModelProperty(value = "凭取货码关闭柜子")
    @RequestMapping(path = "closeByCarrierCode",method = RequestMethod.PATCH)
    public void closeCabinetByCarrierCode(@RequestParam(value = "carrier_code")String carrier_code)throws Exception{
        cabinetService.closeCabinetByCarrierCode(carrier_code);
    }

//    @ApiModelProperty(value = "查看单个自提柜状态")
//    @RequestMapping(path ="showState",method = RequestMethod.GET)
//    public Cabinet showState(@RequestParam(value = "cabinet_id") String cabinet_id)throws Exception{
//        return  cabinetService.showState(cabinet_id);
//    }
//
//    @ApiModelProperty(value = "查看整个柜子的状态")
//    @RequestMapping(path = "showStateByCupboard",method = RequestMethod.GET)
//    public List<Cabinet> showStateByCupboard(@RequestParam(value = "cupboard_id") String cupboard_id) throws Exception{
//        return cabinetService.showStateByCupBoard(cupboard_id);
//    }
//
//    @ApiModelProperty(value = "更改自提柜门的状态")
//    @RequestMapping(path = "changeOpen",method = RequestMethod.PATCH)
//    public Cabinet changOpen(@RequestParam(value = "open") String open ,@RequestParam(value = "cabinet_id") String cabinet_id) throws Exception{
//        return cabinetService.changeOpen(open,cabinet_id);
//    }
}
