package com.selfcabinet.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cupboard {
    @ApiModelProperty(value = "自提柜id")
    private String cupboard_id;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "自提柜行数")
    private int line;

    @ApiModelProperty(value = "自提柜列数")
    private int row;

    @ApiModelProperty(value = "自提柜中单个柜子数量")
    private int num;

    @ApiModelProperty(value = "自提柜空闲的柜子数量")
    private int spare_num;

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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSpare_num() {
        return spare_num;
    }

    public void setSpare_num(int spare_num) {
        this.spare_num = spare_num;
    }

}
