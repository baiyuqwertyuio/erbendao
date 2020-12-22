package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.OrderMapper;
import com.qf.pojo.OrdList;
import com.qf.pojo.Order;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<OrdList> all =orderMapper.findAll();
        baseResp.setCode(200);
        baseResp.setData(all);
        return baseResp;
    }

    @Override
    public Order findOrder(String transferId) {

        return orderMapper.findOrder(transferId);

    }

    @Override
    public BaseResp findById(Integer id) {
        BaseResp baseResp = new BaseResp();
        Order byId = orderMapper.findById(id);
        if(byId!=null){
            baseResp.setData(byId);
            baseResp.setCode(200);

        }else {
            baseResp.setCode(201);
        }
        return baseResp;
    }

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }

    @Override
    public int delete(Integer id) {
        return orderMapper.delete(id);
    }

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public List<OrdList> outAll() {
        return orderMapper.findAll();
    }
}
