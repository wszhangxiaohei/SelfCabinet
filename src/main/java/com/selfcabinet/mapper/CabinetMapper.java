package com.selfcabinet.mapper;

import com.selfcabinet.model.Cabinet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CabinetMapper {

    @Select("SELECT * FROM cabinet_info")
    List<Cabinet> getAll();

    @Select("SELECT * FROM cabinet_info WHERE cabinet_id = #{cabinet_id}")
    Cabinet getById(@Param("cabinet_id") String cabinet_id);

    @Select("SELECT * FROM cabinet_info WHERE cupboard_id = #{cupboard_id} and no =#{no}" )
    Cabinet getByCupAndNo(@Param("cupboard_id") String cupboard_id,@Param("no") String no);

    @Select("SELECT * FROM cabinet_info WHERE bar_id = #{bar_id}")
    List<Cabinet> getByBarId(@Param("bar_id") String bar_id);

    @Select("SELECT * FROM cabinet_info WHERE cupboard_id = #{cupboard_id}")
    List<Cabinet> getByCupboardId(@Param("cupboard_id") String cupboard_id);

    @Insert("INSERT INTO cabinet_info(cabinet_id,cupboard_id,bar_id,line,row,no,used,state,open)" +
            "VALUES(#{cabinet_id},#{cupboard_id},#{bar_id},#{line},#{row},#{no},#{used},#{state},#{open});")
    void insert(Cabinet cabinet);

    @Update("UPDATE cabinet_info SET open =#{open} WHERE cabinet_id=#{cabinet_id}")
    void updateOpenById(@Param("open") String open,@Param("cabinet_id") String cabinet_id);

    @Update("UPDATE cabinet_info SET used =#{used} WHERE cabinet_id=#{cabinet_id}")
    void updateUsedById(@Param("used") String used,@Param("cabinet_id") String cabinet_id);

    @Update("UPDATE cabinet_info SET state =#{state} WHERE cabinet_id=#{cabinet_id}")
    void updateStateById(@Param("state") String state,@Param("cabinet_id") String cabinet_id);

}
