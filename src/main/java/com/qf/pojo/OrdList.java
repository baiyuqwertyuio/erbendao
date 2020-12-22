package com.qf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("orders")
public class OrdList {

    @Excel(name = "编号")
    private Integer id;

    @Excel(name = "商品")
    private String name;

    @ExcelIgnore
    private String  transferId;

    @Excel(name = "价格")
    private Double price;

    @Excel(name = "用户名")
    private String userName;
}
