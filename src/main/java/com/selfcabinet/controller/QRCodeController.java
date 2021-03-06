package com.selfcabinet.controller;

import com.selfcabinet.service.QRCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/QRCode")
@RestController
@EnableAutoConfiguration
@Api(tags = "QRCode", description = "生成解析二维码(产生测试二维码)")
public class QRCodeController {
    private QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService){
        this.qrCodeService=qrCodeService;
    }

    @ApiOperation(value = "作二维码图片")
    @RequestMapping(path = "create",method = RequestMethod.POST)
    public void create(@RequestParam(value = "order_id") String order_id,@RequestParam(value = "cupboard_id") String cupboard_id,@RequestParam(value = "type") String type,@RequestParam(value = "carrier_code") String carrier_code,@RequestParam(value = "address") String address) throws Exception {
        qrCodeService.createQRCode(order_id,cupboard_id,type,carrier_code,address);
    }

    @ApiOperation(value = "读取二维码内容")
    @RequestMapping(path = "read",method= RequestMethod.GET)
    public String read(@RequestParam(value = "address") String address){
        return qrCodeService.readQRCode(address);
    }
}
