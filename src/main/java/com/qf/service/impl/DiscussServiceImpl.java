package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.DiscussMapper;
import com.qf.pojo.Discuss;
import com.qf.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService {

    @Autowired
    DiscussMapper discussMapper;

    @Override
    public BaseResp findByMid(Integer mid) {

        //定义封装类
        BaseResp baseResp = new BaseResp();

        List<Discuss> discusses = discussMapper.findByMid(mid);

        //判空
        if (!discusses.isEmpty()) {

            baseResp.setCode(200);

            baseResp.setMsg("查询成功");

            baseResp.setData(discusses);

            return baseResp;

        }

        baseResp.setCode(201);

        baseResp.setMsg("查询失败");

        return baseResp;

    }

    @Override
    public BaseResp insertDiscuss(Discuss discuss) {

        BaseResp baseResp = new BaseResp();

        if (discuss != null) {

            discussMapper.insertDiscuss(discuss);

            baseResp.setCode(200);

            baseResp.setMsg("新增成功");

            return baseResp;

        }

        System.out.println("获取失败");

        baseResp.setCode(201);

        baseResp.setMsg("添加失败");

        return baseResp;
    }
}
