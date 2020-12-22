package com.qf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qf.common.BaseResp;
import com.qf.dao.UserRepository;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
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
            user.setMembers(0);
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
//        User byEmail = userMapper.fingByEmail(email);
        if (user.getPassword() != null && user.getPassword().equals(byEmail.getPassword())){
            UUID uuid = UUID.randomUUID();
            redisUtils.set(uuid.toString(),byEmail);
            byEmail.setToken(uuid.toString());
            userRepository.saveAndFlush(byEmail);
            baseResp.setData(uuid.toString());
            baseResp.setCode(200);
            return baseResp;
        }
        baseResp.setCode(2004);
        baseResp.setMsg("密码或邮箱错误！");
        return baseResp;
    }

    //后台管理方法
    @Override
    public BaseResp findAll(Integer page, Integer limit) {
        PageRequest pageRequest = new PageRequest(page - 1, limit);
        BaseResp baseResp = new BaseResp();
        Page<User> all = userRepository.findAll(pageRequest);
        baseResp.setData(all.getContent());
        baseResp.setCount(all.getTotalElements());
        baseResp.setCode(200);
        return baseResp;
    }

    @Override
    public BaseResp findById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        BaseResp baseResp = new BaseResp();
        if(byId.isPresent()){
            baseResp.setData(byId.get());
            baseResp.setCode(200);
            baseResp.setMsg("成功");
        }else {
            baseResp.setCode(201);
            baseResp.setMsg("失败");
        }
        return baseResp;
    }

    @Override
    public BaseResp saveOrUpdate(User user) {
        User user1 = userRepository.saveAndFlush(user);
        BaseResp baseResp = new BaseResp();
        //修改
        if(user1!=null&&user1.getId()!=null){
            baseResp.setData(user1);
            baseResp.setCode(200);
            baseResp.setMsg("新增成功");
        }else if(user1!=null&&user1.getId()==null){
            //新增
            baseResp.setData(user1);
            baseResp.setCode(200);
            baseResp.setMsg("新增成功");
        }
        return baseResp;
    }

    @Override
    public BaseResp deleteById(Integer id) {
        BaseResp baseResp = new BaseResp();
        userRepository.deleteById(id);
        baseResp.setCode(200);
        baseResp.setMsg("删除失败");
        return baseResp;
    }

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
    public List<User> outAll() {
        List<User> all = userRepository.findAll();
        return all;
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
