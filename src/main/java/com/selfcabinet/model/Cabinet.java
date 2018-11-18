package com.selfcabinet.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cabinet {
    @ApiModelProperty(value = "自提柜空闲")
    public static final String SPARE="0";

    @ApiModelProperty(value = "自提柜被预定，商品未到")
    public static final String ORDER="1";

    @ApiModelProperty(value = "自提柜正在使用中，商品已到")
    public static final String USED="2";

    @ApiModelProperty(value = "柜子正常")
    public static final String NORMAL="0";

    @ApiModelProperty(value = "柜子异常")
    public static final String ABNORMAL="1";

    @ApiModelProperty(value = "打开柜子")
    public static final String OPEN="1";

    @ApiModelProperty(value = "关闭柜子")
    public static final String CLOSED="0";

    @ApiModelProperty(value = "单个柜子id")
    private String cabinet_id;

    @ApiModelProperty(value = "自提柜id")
    private String cupboard_id;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "单个柜子所在行")
    private String line;

    @ApiModelProperty(value = "单个柜子所在列")
    private String row;

    @ApiModelProperty(value = "单个柜子的编号")
    private String no;

    @ApiModelProperty(value = "单个柜子的忙闲状态")
    private String used;

    @ApiModelProperty(value = "单个柜子的硬件状态")
    private String state;

    @ApiModelProperty(value = "单个柜子开关")
    private String open;

    public Cabinet(){

    }

    public void setCabinet(Cabinet cabinet){
        this.cabinet_id=cabinet.cabinet_id;
        this.cupboard_id=cabinet.cupboard_id;
        this.bar_id=cabinet.bar_id;
        this.line=cabinet.line;
        this.row=cabinet.row;
        this.no=cabinet.no;
        this.used=cabinet.used;
        this.state=cabinet.state;
        this.open=cabinet.open;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
