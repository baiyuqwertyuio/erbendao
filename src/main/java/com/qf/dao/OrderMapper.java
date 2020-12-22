package com.qf.dao;

import com.qf.pojo.OrdList;
import com.qf.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrdList> findAll();

    Order findById(@Param("id") Integer id);

    int update(Order order);

    int delete(@Param("id") Integer id);

    int insert(Order order);

    Order findOrder(@Param("transferId") String transferId);

    int insertOrder(Order order);
}
