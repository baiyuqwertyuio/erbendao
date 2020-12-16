package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.Movie;

public interface MovieService {
    BaseResp findAll();
    BaseResp findBySid(Integer sid);
    BaseResp findById(Integer id);
    BaseResp findByVip(Integer vip);
    BaseResp findBigPic();
    BaseResp searchByName(String like);
    BaseResp uploading(Movie movie);
}
