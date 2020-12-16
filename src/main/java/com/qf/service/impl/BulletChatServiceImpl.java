package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.BulletChatRepository;
import com.qf.pojo.BulletChat;
import com.qf.service.BulletChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletChatServiceImpl implements BulletChatService {

    @Autowired
    BulletChatRepository bulletChatRepository;

    @Override
    public BaseResp findByMid(Integer mid) {
        BaseResp baseResp = new BaseResp();
        List<BulletChat> byMid = bulletChatRepository.findByMid(mid);
        baseResp.setCode(200);
        baseResp.setData(byMid);
        baseResp.setMsg("查询成功");
        return baseResp;
    }
}
