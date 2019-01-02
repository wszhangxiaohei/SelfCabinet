package com.selfcabinet.model;

import io.swagger.annotations.ApiModelProperty;

public class StimulateOrder {

    @ApiModelProperty(value = "订单id")
    private String order_id;

    @ApiModelProperty(value = "柜子id")
    private String cupboard_id;

    @ApiModelProperty(value = "类型")
    private  String type;

    @ApiModelProperty (value = "取货码")
    private  String carrier_code;

    @ApiModelProperty(value = "订单状态")
    private String status;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCupboard_id() {
        return cupboard_id;
    }

    public void setCupboard_id(String cupboard_id) {
        this.cupboard_id = cupboard_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarrier_code() {
        return carrier_code;
    }

    public void setCarrier_code(String carrier_code) {
        this.carrier_code = carrier_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
