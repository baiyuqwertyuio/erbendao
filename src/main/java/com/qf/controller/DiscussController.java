package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.pojo.Discuss;
import com.qf.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论实现层
 */

@RestController
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    DiscussService discussService;

    @RequestMapping("/findByMid")
    public BaseResp findByMid(@RequestParam("id") Integer id) {

        return discussService.findByMid(id);

    }

    @RequestMapping("/insertDiscuss")
    public BaseResp insertDiscuss(@RequestBody Discuss discuss) {

        return discussService.insertDiscuss(discuss);

    }


}
