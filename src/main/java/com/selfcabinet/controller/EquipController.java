package com.selfcabinet.controller;


import com.selfcabinet.mapper.EquipMapper;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.Equip;
import com.selfcabinet.service.CabinetService;
import com.selfcabinet.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/equip")
@RestController
@EnableAutoConfiguration
@Api(tags = "Equip", description = "模拟硬件操作")
public class EquipController {

    private final EquipMapper equipMapper;

    @Autowired
    public EquipController(EquipMapper equipMapper){
        this.equipMapper=equipMapper;
    }

    @ApiModelProperty(value = "查询单个柜子")
    @RequestMapping(path = "query",method = RequestMethod.GET)
    public Equip queryBox(@RequestParam(value = "boxNo")String boxNo)throws Exception{
        return equipMapper.getDoorByBoxNo(boxNo);
    }

    @ApiModelProperty(value = "查询一组柜子")
    @RequestMapping(path = "queryGroup",method = RequestMethod.GET)
    public List<Equip> queryBoxGroup(@RequestParam(value = "groupNo")String groupNo)throws Exception{
        return equipMapper.getDoorByGroupNo(groupNo);
    }

    @ApiModelProperty(value = "打开单个柜子")
    @RequestMapping(path = "open",method = RequestMethod.PATCH)
    public void openBox(@RequestParam(value = "boxNo")String boxNo)throws Exception{
        equipMapper.openBox(boxNo);
    }

    @ApiModelProperty(value = "打开一组柜子")
    @RequestMapping(path = "openGroup",method = RequestMethod.PATCH)
    public void openBoxGroup(@RequestParam(value = "groupNo")String groupNo)throws Exception{
        equipMapper.openBoxGroup(groupNo);
    }

    @ApiModelProperty(value = "关闭单个柜子")
    @RequestMapping(path = "close",method = RequestMethod.PATCH)
    public void closeBox(@RequestParam(value = "boxNo")String boxNo)throws Exception{
        equipMapper.closeBox(boxNo);
    }

    @ApiModelProperty(value = "关闭一组柜子")
    @RequestMapping(path = "closeGroup",method = RequestMethod.PATCH)
    public void closeBoxGroup(@RequestParam(value = "groupNo")String groupNo)throws Exception{
        equipMapper.closeBoxGroup(groupNo);
    }

    @ApiModelProperty(value = "送货")
    @RequestMapping(path = "put",method = RequestMethod.PATCH)
    public void put(@RequestParam(value = "boxNo")String boxNo)throws Exception{
        equipMapper.put(boxNo);
    }

    @ApiModelProperty(value = "取货")
    @RequestMapping(path = "get",method = RequestMethod.PATCH)
    public void get(@RequestParam(value = "boxNo")String boxNo)throws Exception{
        equipMapper.get(boxNo);
    }
}
