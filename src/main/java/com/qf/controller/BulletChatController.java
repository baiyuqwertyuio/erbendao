package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.BulletChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/bulletchat")
public class BulletChatController {

    @Autowired
    BulletChatService bulletChatService;


    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResp findAll(@RequestBody Map map){

        return bulletChatService.findByMid((Integer)map.get("mid"));
    }

}
