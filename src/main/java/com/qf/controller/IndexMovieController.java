package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.IndexMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@RequestMapping("/indexmovie")
public class IndexMovieController {

    @Autowired
    IndexMovieService indexMovieService;


    @RequestMapping("/findAll")
    public BaseResp findAll(){
        BaseResp all = indexMovieService.findAll();
        return all;
    }
}
