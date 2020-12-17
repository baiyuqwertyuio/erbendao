package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.IndexTvRepository;
import com.qf.pojo.IndexTv;
import com.qf.service.IndexTvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IndexTvServiceImpl implements IndexTvService {
    
    @Autowired
    IndexTvRepository indexTvRepository;
    
    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<IndexTv> all = indexTvRepository.findAll();
        baseResp.setData(all);
        baseResp.setCode(200);
        baseResp.setMsg("成功");
        return baseResp;
    }
}
