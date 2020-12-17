package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserUtils userUtils;

    @RequestMapping(value = "/sendMail",method = RequestMethod.POST)
    public BaseResp sendMail(@RequestBody Map map){
        String email = (String)map.get("email");

        return userService.send(email);
    }

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public BaseResp registry(@RequestBody User user){
        return userService.registry(user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody User user){
        return userService.login(user);
    }


    @RequestMapping(value = "/getuser",method = RequestMethod.GET)
    public BaseResp getUser(HttpServletRequest request,@RequestParam String token){
        BaseResp baseResp = new BaseResp();
        User user = userUtils.getUser(request,token);
        baseResp.setCode(200);
        baseResp.setData(user);
        baseResp.setMsg("查询成功");
        return baseResp;
    }

}
