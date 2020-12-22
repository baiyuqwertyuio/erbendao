package com.qf.service;

import com.qf.common.BaseResp;

public interface SecondeService {
    BaseResp selectByCid(Integer cid);

    BaseResp selectAll();
}
