package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.User;

public interface UserService {
    BaseResp send(String email);

    BaseResp registry(User user);

    BaseResp login(User user);
}
