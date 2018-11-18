package com.selfcabinet.model;

import io.swagger.annotations.ApiModelProperty;

public class Equip {

    @ApiModelProperty(value = "设备ip")
    private String ip;

    @ApiModelProperty(value = "设备组号")
    private String groupNo;

    @ApiModelProperty(value = "设备编号")
    private String boxNo;

    @ApiModelProperty(value = "门的状态")
    private String doorStatus;

    @ApiModelProperty(value = "柜内是否有物品")
    private String hasItem;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getHasItem() {
        return hasItem;
    }

    public void setHasItem(String hasItem) {
        this.hasItem = hasItem;
    }
}

