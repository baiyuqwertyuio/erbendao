package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.SecondeRepository;
import com.qf.pojo.Seconde;
import com.qf.service.SecondeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondeServiceImpl implements SecondeService {
    @Autowired
    SecondeRepository secondeRepository;

    @Override
    public BaseResp selectByCid(Integer cid) {
        BaseResp baseResp = new BaseResp();
        List<Seconde> byCid = secondeRepository.findByCid(cid);
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        baseResp.setData(byCid);
        return baseResp;
    }

    @Override
    public BaseResp selectAll() {
        BaseResp baseResp = new BaseResp();
        List<Seconde> all = secondeRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        baseResp.setData(all);
        return baseResp;
    }
}
