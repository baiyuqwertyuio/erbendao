package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.UserRepository;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public BaseResp send(String email) {
        BaseResp baseResp = new BaseResp();
        if (email != null){
            User user = userRepository.findByEmail(email);
            if (user != null){
                baseResp.setCode(301);
                baseResp.setMsg("该邮箱已被使用");
                return baseResp;
            }

            Random random = new Random();
            StringBuffer code = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                code.append(random.nextInt(10));
            }
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("plipli验证码");
            simpleMailMessage.setText(code.toString());
            javaMailSender.send(simpleMailMessage);
            redisUtils.set(email,code.toString());
            //设置一个过期时间
            redisUtils.expire(email,300);
            baseResp.setCode(200);
            baseResp.setMsg("验证码发送成功！");
            return baseResp;

        }
        baseResp.setCode(2002);
        baseResp.setMsg("邮箱不能为Null");
        return baseResp;
    }

    @Override
    public BaseResp registry(User user) {
        BaseResp baseResp = new BaseResp();
        String userName = user.getUserName();
        User byUserName = userRepository.findByUserName(userName);
        if (byUserName != null){
            baseResp.setCode(301);
            baseResp.setMsg("用户名已存在");
            return baseResp;
        }
        String code = user.getCode();
        String email = user.getEmail();

        String o =(String) redisUtils.get(email);
        if (o != null && code.equals(o)){
            userRepository.saveAndFlush(user);
            baseResp.setCode(200);
            baseResp.setMsg("注册成功");
            return baseResp;
        }
        baseResp.setCode(2005);
        baseResp.setMsg("用户注册失败");;
        return baseResp;
    }

    @Override
    public BaseResp login(User user) {
        BaseResp baseResp = new BaseResp();
        String email = user.getEmail();
        User byEmail = userRepository.findByEmail(email);
        if (user.getPassword() != null && user.getPassword().equals(byEmail.getPassword())){
            UUID uuid = UUID.randomUUID();
            redisUtils.set(uuid.toString(),user);
            baseResp.setData(uuid.toString());
            baseResp.setCode(200);
            return baseResp;
        }
        baseResp.setCode(2004);
        baseResp.setMsg("密码或邮箱错误！");
        return baseResp;
    }
}
