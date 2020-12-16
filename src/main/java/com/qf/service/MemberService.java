package com.qf.service;


import com.qf.common.BaseResp;
import com.qf.pojo.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MemberService {
    BaseResp findUserByToken(HttpServletRequest request);

    BaseResp pay(Order order, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
