package com.qf;

import com.alibaba.fastjson.JSONObject;
import com.qf.pojo.User;
import com.qf.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErbendaoApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        Object o = redisUtils.get("36e2dca2-38df-4a1e-8c24-8e38d9d8e949");
        Object o1 = JSONObject.toJSON(o);
        User User = JSONObject.parseObject(o1.toString(), User.class);
        System.out.println("User = " + User);
    }

}
