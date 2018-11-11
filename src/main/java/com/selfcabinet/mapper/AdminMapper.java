package com.selfcabinet.mapper;

import com.selfcabinet.model.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AdminMapper {
    @Select("SELECT * FROM admin_info")
    List<Admin> getAll();

    @Select("SELECT * FROM admin_info WHERE admin_id = #{admin_id}")
    Admin getById(@Param("admin_id") String admin_id);

    @Select("SELECT state FROM admin_info WHERE admin_id = #{admin_id}")
    String  getStateById(@Param("admin_id") String admin_id);

    @Select("SELECT * FROM admin_info WHERE bar_id = #{bar_id}")
    List<Admin> getByBarId(@Param("bar_id") String bar_id);

    @Insert("INSERT INTO admin_info(admin_id,password,bar_id,state)" +
            "VALUES(#{admin_id},#{password},#{bar_id},#{state});")
    void insert(Admin admin);

    @Update("UPDATE admin_info SET password =#{password} WHERE admin_id=#{admin_id}")
    void updatePasswordById(@Param("password") String password,@Param("admin_id") String admin_id);

    @Update("UPDATE admin_info SET state =#{state} WHERE admin_id=#{admin_id}")
    void updateStateById(@Param("state") String state,@Param("admin_id") String admin_id);

}
