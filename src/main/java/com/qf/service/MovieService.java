package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.Movie;

import java.util.List;

public interface MovieService {
    //前台方法
    BaseResp findAll();
    BaseResp findBySid(Integer sid);
    BaseResp findById(Integer id);
    BaseResp findByVip(Integer vip);
    BaseResp findBigPic();
    BaseResp searchByName(String like);
//    BaseResp uploading(Movie movie);


    //后台方法
    BaseResp selectAll();
    BaseResp insertOrUpdate(Movie movie);
    BaseResp delete(Integer id);

    List<Movie> outAll();

}
