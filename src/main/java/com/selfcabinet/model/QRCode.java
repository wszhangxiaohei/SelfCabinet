package com.selfcabinet.model;

import io.swagger.annotations.ApiModelProperty;

public class QRCode {
    private String order_id;

    private String user_id;

    private String type;

    public static final String USER="USER";
    public static final String COURIER="COURIER";

    @ApiModelProperty(value = "二维码内容字符串")
    private String QRContentToken;

    @ApiModelProperty(value = "提示信息")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQRContentToken() {
        return QRContentToken;
    }

    public void setQRContentToken(String QRContentToken) {
        this.QRContentToken = QRContentToken;
    }
}
