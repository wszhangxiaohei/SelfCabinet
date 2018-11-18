package com.selfcabinet.mapper;

import com.selfcabinet.model.Equip;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EquipMapper {

    //查询单个格口状态
    @Select("SELECT * FROM equip_info WHERE boxNo = #{boxNo}")
    Equip  getDoorByBoxNo(@Param("boxNo") String boxNo);

    //查询所有格口状态
    @Select("SELECT * FROM equip_info WHERE groupNo = #{groupNo}")
    List<Equip>  getDoorByGroupNo(@Param("groupNo") String groupNo);

    //打开单个格口
    @Update("UPDATE equip_info SET doorStatus ='1' WHERE boxNo=#{boxNo}")
    void openBox(@Param("boxNo") String boxNo);

    //打开一组格口
    @Update("UPDATE equip_info SET doorStatus ='1' WHERE groupNo=#{groupNo}")
    void openBoxGroup(@Param("groupNo") String groupNo);


    //关闭单个格口
    @Update("UPDATE equip_info SET doorStatus ='0' WHERE boxNo=#{boxNo}")
    void closeBox(@Param("boxNo") String boxNo);

    //关闭一组格口
    @Update("UPDATE equip_info SET doorStatus ='0' WHERE groupNo=#{groupNo}")
    void closeBoxGroup(@Param("groupNo") String groupNo);

    //送货
    @Update("UPDATE equip_info SET hasItem ='0' WHERE boxNo=#{boxNo}")
    void put(@Param("boxNo") String boxNo);

    //取货
    @Update("UPDATE equip_info SET hasItem ='1' WHERE boxNo=#{boxNo}")
    void get(@Param("boxNo") String boxNo);

}
