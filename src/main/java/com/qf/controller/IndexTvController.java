package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.IndexTvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@RequestMapping("/indextv")
public class IndexTvController {

    @Autowired
    IndexTvService indexTvService;

    @RequestMapping("/findAllTv")
    public BaseResp findAll(){
        return indexTvService.findAll();
    }

}
