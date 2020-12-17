package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.IndexMovieRepository;
import com.qf.pojo.IndexMovie;
import com.qf.service.IndexMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexMovieServiceImpl implements IndexMovieService {

    @Autowired
    IndexMovieRepository indexMovieRepository;

    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<IndexMovie> all = indexMovieRepository.findAll();
        baseResp.setData(all);
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        return baseResp;
    }
}
