package com.selfcabinet.mapper;


import com.selfcabinet.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT * FROM order_info")
    List<Order> getAll();

    @Select("SELECT COUNT(*) FROM order_info WHERE order_id = #{order_id}")
    int getIdNumById(@Param("order_id") String order_id);

    @Select("SELECT COUNT(*) FROM order_info WHERE carrier_code = #{carrier_code}")
    int getIdNumByCarrierCode(@Param("carrier_code") String carrier_code);

    @Select("SELECT * FROM order_info WHERE order_id = #{order_id}")
    Order getById(@Param("order_id") String order_id);

    @Select("SELECT * FROM order_info WHERE carrier_code = #{carrier_code}")
    Order getByCarrierCode(@Param("carrier_code") String carrier_code);

    @Insert("INSERT INTO order_info(order_id,bar_id,cabinet_id,cupboard_id,state,carrier_code)" +
            "VALUES(#{order_id},#{bar_id},#{cabinet_id},#{cupboard_id},#{state},#{carrier_code});")
    void insert(Order order);

    @Update("UPDATE order_info SET state =#{state} WHERE order_id=#{order_id}")
    void updateStateById(@Param("state") String state,@Param("order_id") String order_id);

    @Update("UPDATE order_info SET cupboard_id =#{cupboard_id} WHERE order_id=#{order_id}")
    void updateCupBoardById(@Param("cupboard_id") String cupboard_id,@Param("order_id") String order_id);

    @Update("UPDATE order_info SET cabinet_id =#{cabinet_id} WHERE order_id=#{order_id}")
    void updateCabinetIdById(@Param("cabinet_id") String cabinet_id,@Param("order_id") String order_id);

    @Update("UPDATE order_info SET bar_id =#{bar_id} WHERE order_id=#{order_id}")
    void updateBarIdById(@Param("bar_id") String bar_id,@Param("order_id") String order_id);

    @Select("SELECT * FROM order_info WHERE state ='0' ")
    List<Order> getUndoneOrder();
}
