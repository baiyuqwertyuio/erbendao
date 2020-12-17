package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.Discuss;

/**
 * 评论Service层
 */


public interface DiscussService {

    BaseResp findByMid(Integer mid);

    BaseResp insertDiscuss(Discuss discuss);

}
