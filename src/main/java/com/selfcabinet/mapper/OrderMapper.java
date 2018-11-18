package com.selfcabinet.mapper;


import com.selfcabinet.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT * FROM order_info")
    List<Order> getAll();

    @Select("SELECT * FROM order_info WHERE order_id = #{order_id}")
    Order getById(@Param("order_id") String order_id);


    @Insert("INSERT INTO order_info(order_id,bar_id,cabinet_id,cupboard_id,state)" +
            "VALUES(#{order_id},#{bar_id},#{cabinet_id},#{cupboard_id},#{state});")
    void insert(Order order);

    @Update("UPDATE order_info SET state =#{state} WHERE order_id=#{order_id}")
    void updateStateById(@Param("state") String state,@Param("order_id") String order_id);

    @Update("UPDATE order_info SET cupboard_id =#{cupboard_id} WHERE order_id=#{order_id}")
    void updateCupBoardById(@Param("cupboard_id") String cupboard_id,@Param("order_id") String order_id);

    @Update("UPDATE order_info SET cabinet_id =#{cabinet_id} WHERE order_id=#{order_id}")
    void updateCabinetById(@Param("cabinet_id") String cabinet_id,@Param("order_id") String order_id);

    @Update("UPDATE order_info SET cabinet_used =#{cabinet_id} WHERE order_id=#{order_id}")
    void updateCabinetUsedById(@Param("cabinet_used") String cabinet_used,@Param("order_id") String order_id);

}
