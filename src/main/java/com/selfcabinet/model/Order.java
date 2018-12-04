package com.selfcabinet.model;

import io.swagger.annotations.ApiModelProperty;

public class Order {
    @ApiModelProperty(value = "订单id")
    private String order_id;


    @ApiModelProperty(value = "单个柜子id")
    private String cabinet_id;

    @ApiModelProperty(value = "自提柜id")
    private String cupboard_id;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "订单当前状态")
    private String state;

    @ApiModelProperty(value = "取货码")
    private String carrier_code;

    @ApiModelProperty(value = "订单未完成")
    public static final String UNDONE="0";

    @ApiModelProperty(value = "订单完成")
    public static final String DONE="1";

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCabinet_id() {
        return cabinet_id;
    }

    public void setCabinet_id(String cabinet_id) {
        this.cabinet_id = cabinet_id;
    }

    public String getCupboard_id() {
        return cupboard_id;
    }

    public void setCupboard_id(String cupboard_id) {
        this.cupboard_id = cupboard_id;
    }

    public String getBar_id() {
        return bar_id;
    }

    public void setBar_id(String bar_id) {
        this.bar_id = bar_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCarrier_code() {
        return carrier_code;
    }

    public void setCarrier_code(String carrier_code) {
        this.carrier_code = carrier_code;
    }
}
