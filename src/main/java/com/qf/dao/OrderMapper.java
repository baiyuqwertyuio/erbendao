package com.qf.dao;

import com.qf.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
}
