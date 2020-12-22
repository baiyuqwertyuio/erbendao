package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.OrdList;
import com.qf.pojo.Order;

import java.util.List;


public interface OrderService {

    BaseResp findAll();

    BaseResp findById(Integer id);

    Order findOrder(String transferId);

    int update(Order order);

    int delete(Integer id);

    int insert(Order order);

    List<OrdList> outAll();
}
