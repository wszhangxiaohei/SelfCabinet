package com.selfcabinet.mapper;
;
import com.selfcabinet.model.Cupboard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CupboardMapper {
    @Select("SELECT * FROM cupboard_info")
    List<Cupboard> getAll();

    @Select("SELECT * FROM cupboard_info WHERE cupboard_id = #{cupboard_id}")
    Cupboard getById(@Param("cupboard_id") String cupboard_id);

    @Select("SELECT * FROM cupboard_info WHERE bar_id = #{bar_id}")
    List<Cupboard> getByBarId(@Param("bar_id") String bar_id);

    @Select("SELECT COUNT(spare_num) FROM cupboard_info WHERE bar_id=#{bar_id}")
    int getSpareNum(@Param("bar_id") String bar_id);

    @Select("SELECT spare_num FROM cupboard_info WHERE cupboard_id=#{cupboard_id}")
    int getSpareNumByCupboardId(@Param("cupboard_id")String cupboard_id);

    @Insert("INSERT INTO cupboard_info(cupboard_id,bar_id,line,row,num,spare_num)" +
            "VALUES(#{cupboard_id},#{bar_id},#{line},#{row},#{num},#{spare_num});")
    void insert(Cupboard cupboard);

    @Update("UPDATE cupboard_info SET spare_num =#{spare_num} WHERE cupboard_id=#{cupboard_id}")
    void updateSpareNumbyId(@Param("spare_num") int spare_num,@Param("cupboard_id") String cupboard_id);

}