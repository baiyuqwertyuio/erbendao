package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

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


}
