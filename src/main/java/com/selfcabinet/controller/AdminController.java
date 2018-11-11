package com.selfcabinet.controller;

import com.selfcabinet.model.Admin;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/admin")
@RestController
@EnableAutoConfiguration
@Api(tags = "Admin", description = "管理员相关操作")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){this.adminService=adminService;}

    @ApiModelProperty(value = "管理员登录")
    @RequestMapping(path="login",method = RequestMethod.GET)
    public String login(@RequestBody Admin admin)throws Exception{
        return adminService.login(admin);
    }

    @ApiModelProperty(value = "管理员更新密码")
    @RequestMapping(path="updatePassword",method = RequestMethod.PATCH)
    public String updatePassword(@RequestBody Admin admin, @RequestParam(value = "newPassword") String newPassword)throws Exception{
        return adminService.updatePassword(admin,newPassword);
    }

    @ApiModelProperty(value = "管理员退出登录")
    @RequestMapping(path="logout",method = RequestMethod.PATCH)
    public void logout(@RequestParam(value = "admin_id") String admin_id)throws Exception{
        adminService.logout(admin_id);
    }

    @ApiModelProperty(value = "管理员查看柜子状态")
    @RequestMapping(path="showStatus",method = RequestMethod.GET)
    public List<Cabinet> showStatus(@RequestParam(value = "admin_id") String admin_id,@RequestParam(value = "cupboard_id") String cupboard_id)throws Exception{
        return adminService.showStatus(admin_id,cupboard_id);
    }

    @ApiModelProperty(value = "管理员打开自提柜")
    @RequestMapping(path="openCabinet",method = RequestMethod.GET)
    public Cabinet openCabinet(@RequestParam(value = "admin_id") String admin_id,@RequestParam(value = "cabinet_id") String cabinet_id)throws Exception{
        return adminService.openCabinet(admin_id,cabinet_id);
    }
}