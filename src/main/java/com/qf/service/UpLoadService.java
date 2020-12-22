package com.qf.service;

import com.qf.common.BaseResp;
import com.qf.pojo.UpLoad;


public interface UpLoadService {

    BaseResp add(UpLoad upLoad);

    BaseResp videoFindByUid(Integer uid);

    BaseResp del(Integer id);

}
