package com.selfcabinet.controller;

import com.selfcabinet.service.QRCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/QRCode")
@RestController
@EnableAutoConfiguration
@Api(tags = "QRCode", description = "生成解析二维码")
public class QRCodeController {
    private QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService){
        this.qrCodeService=qrCodeService;
    }

    @ApiModelProperty(value = "产生二维码")
    @RequestMapping(path = "create")
    public void create(@RequestParam(value = "order_id") String order_id,@RequestParam(value = "type") String type){
        qrCodeService.createQRCode(order_id,type);
    }

    @ApiModelProperty(value = "读取二维码")
    @RequestMapping(path = "read")
    public String read(){
        return qrCodeService.readQRCode();
    }
}