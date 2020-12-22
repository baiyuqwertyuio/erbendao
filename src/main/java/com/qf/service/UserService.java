package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    BaseResp send(String email);

    BaseResp registry(User user);

    BaseResp login(User user);

    //后台管理方法
    BaseResp findAll(Integer page, Integer limit);

    BaseResp findById(Integer id);

    BaseResp saveOrUpdate(User user);

    BaseResp deleteById(Integer id);

    BaseResp findUserByToken(HttpServletRequest request);

    List<User> outAll();

}
