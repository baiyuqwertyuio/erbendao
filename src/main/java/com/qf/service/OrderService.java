package com.qf.service;

import com.qf.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {

    Order findOrder(String transferId);
}
