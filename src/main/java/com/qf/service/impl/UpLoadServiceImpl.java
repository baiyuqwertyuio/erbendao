package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.UpLoadMapper;
import com.qf.pojo.UpLoad;
import com.qf.service.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpLoadServiceImpl implements UpLoadService {

    @Autowired
    UpLoadMapper upLoadMapper;


    @Override
    public BaseResp add(UpLoad upLoad) {
        BaseResp baseResp = new BaseResp();

        if (upLoad!=null&& upLoad.getVideoUrl()!= ""&&upLoad.getCatalog()!=""&&upLoad.getDes()!=""&&upLoad.getPic()!=""){
            upLoadMapper.add(upLoad);
            baseResp.setCode(200);
            baseResp.setMsg("增加成功");
            return baseResp;
        }else {
            baseResp.setCode(201);
            baseResp.setMsg("增加失败");
            return baseResp;
        }

    }

    @Override
    public BaseResp videoFindByUid(Integer uid) {
        BaseResp baseResp = new BaseResp();
        List<UpLoad> upLoads = upLoadMapper.videoFindByUid(uid);
        if (upLoads != null){
            System.out.println(upLoads);
            baseResp.setCode(200);
            baseResp.setData(upLoads);
            baseResp.setMsg("查询成功");
            return baseResp;
        }else {
            baseResp.setCode(200);
            baseResp.setMsg("查询失败");
            return baseResp;
        }

    }

    @Override
    public BaseResp del(Integer id) {
        BaseResp baseResp = new BaseResp();
        upLoadMapper.del(id);
        baseResp.setCode(200);
        baseResp.setMsg("删除成功");
        return baseResp;
    }
}
