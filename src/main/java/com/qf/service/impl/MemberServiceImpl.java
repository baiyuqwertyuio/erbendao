package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.common.AlipayUtils;
import com.qf.common.BaseResp;
import com.qf.dao.OrderMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.Order;
import com.qf.pojo.User;
import com.qf.service.MemberService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseResp findUserByToken(HttpServletRequest request) {
        String token = getToken(request);
        Object o = redisUtils.get(token);
        Object o1 = JSONObject.toJSON(o);
        User user = JSONObject.parseObject(o1.toString(), User.class);
        BaseResp baseResp = new BaseResp();
        baseResp.setData(user);
        baseResp.setMsg("获取用户成功");
        baseResp.setCode(200);
        return baseResp;
    }

    @Override
    public BaseResp pay(Order order, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = getToken(request);
        Object o = redisUtils.get(token);
        Object o1 = JSONObject.toJSON(o);
        User user = JSONObject.parseObject(o1.toString(), User.class);
        String name = order.getName();
        double price = order.getPrice();
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String replace = s.replace("-", "");
        Order order1 = new Order();
        order1.setName(name);
        order1.setPrice(price);
        order1.setUid(user.getId());
        order1.setTransferId(replace);
        orderMapper.insertOrder(order1);
        AlipayUtils alipayUtils = new AlipayUtils();
        String pay = alipayUtils.pay(request, response, order);
        BaseResp baseResp = new BaseResp();
        baseResp.setData(pay);
        return baseResp;
    }

    public String getToken(HttpServletRequest request) {
        String token = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        return token;
    }
}
