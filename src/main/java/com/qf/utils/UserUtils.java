package com.qf.utils;

import com.alibaba.fastjson.JSONObject;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtils {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RedisTemplate redisTemplate;


    public User getUser(HttpServletRequest req,String token){

        Object o = redisUtils.get(token);
        Object o1 = JSONObject.toJSON(o);
        User user = JSONObject.parseObject(o1.toString(), User.class);

        return user;
    }

}
