package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.common.UploadUtils;
import com.qf.pojo.Movie;
import com.qf.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//电影前台管理
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    UploadUtils uploadUtils;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResp findAll(){
        return movieService.findAll();
    }

    @RequestMapping(value = "/findBySid",method = RequestMethod.GET)
    public BaseResp findBySid(@RequestParam("sid")Integer sid){
        return movieService.findBySid(sid);
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public BaseResp findById(@RequestParam("id")Integer id){
        return movieService.findById(id);
    }

    @RequestMapping(value = "/findByVip",method = RequestMethod.GET)
    public BaseResp findByVip(@RequestParam("vip")Integer vip){
        return movieService.findByVip(vip);
    }

    @RequestMapping(value = "/findBigPic",method = RequestMethod.GET)
    public BaseResp findBigPic(){
        return movieService.findBigPic();
    }

    @RequestMapping(value = "/searchByName",method = RequestMethod.GET)
    public BaseResp searchByName(@RequestParam("like")String like){
        return movieService.searchByName(like);
    }

    @RequestMapping(value = "/uploading",method = RequestMethod.GET)
    public BaseResp uploading(@RequestBody Movie movie){
        return movieService.insertOrUpdate(movie);
    }
}
