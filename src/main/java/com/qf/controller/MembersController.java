package com.qf.controller;

import com.alibaba.fastjson.JSONObject;
import com.qf.common.BaseResp;
import com.qf.pojo.Order;
import com.qf.pojo.User;
import com.qf.service.MemberService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MembersController {
    @Autowired
    MemberService memberService;


    @RequestMapping(value = "/findUserByToken",method = RequestMethod.POST)
    public BaseResp findUserByToken(HttpServletRequest request) {
        BaseResp baseResp = memberService.findUserByToken(request);
        return baseResp;
    }

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public BaseResp pay(@RequestBody Order order, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseResp pay = memberService.pay(order, request, response);
        return pay;
    }


}
