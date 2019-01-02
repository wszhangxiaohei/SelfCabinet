package com.selfcabinet.mapper;

import com.selfcabinet.model.Order;
import com.selfcabinet.model.StimulateOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StimulateOrderMapper {
    @Select("SELECT * FROM stimulate_order")
    List<StimulateOrder> getAll();

    @Select("SELECT * FROM stimulate_order WHERE status ='0' ")
    List<StimulateOrder> getUndoneOrder();

    @Select("SELECT * FROM stimulate_order WHERE order_id = #{order_id}")
    StimulateOrder getByOrderId(@Param("order_id") String order_id);

    @Insert("INSERT INTO stimulate_order(order_id,cupboard_id,status,carrier_code,type)" +
            "VALUES(#{order_id},#{cupboard_id},#{status},#{carrier_code},#{type});")
    void insert(StimulateOrder stimulateOrder);

    @Update("UPDATE stimulate_order SET status =#{status} WHERE order_id=#{order_id}")
    void updateStatusById(@Param("status") String status,@Param("order_id") String order_id);


}
