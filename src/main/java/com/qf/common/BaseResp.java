package com.qf.common;

import lombok.Data;

@Data
public class BaseResp {

    private Integer code;

    private Object data;

    private String msg;

    private Long count;

}
