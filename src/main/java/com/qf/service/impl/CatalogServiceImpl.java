package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.CatalogRepository;
import com.qf.pojo.Catalog;
import com.qf.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    CatalogRepository catalogRepository;

    @Override
    public BaseResp selectAll() {
        BaseResp baseResp = new BaseResp();
        List<Catalog> all = catalogRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        baseResp.setData(all);
        return baseResp;
    }
}
