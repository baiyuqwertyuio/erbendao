package com.qf.service.impl;

import com.qf.dao.OrderMapper;
import com.qf.pojo.Order;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Order findOrder(String transferId) {

        return orderMapper.findOrder(transferId);

    }
}
