package com.selfcabinet.model;

import io.swagger.annotations.ApiModelProperty;

public class Admin {
    @ApiModelProperty(value = "管理员id")
    private String admin_id;

    @ApiModelProperty(value = "管理员密码")
    private String password;

    @ApiModelProperty(value = "无人吧id")
    private String bar_id;

    @ApiModelProperty(value = "当前管理员登录状态")
    private String state;

    @ApiModelProperty(value = "管理员电话")
    private String tel;

    @ApiModelProperty(value = "管理员姓名")
    private String name;

    @ApiModelProperty(value = "管理员管辖范围")
    private String info;

    public static final String LOGIN="1";
    public static final String LOGOUT="0";

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
