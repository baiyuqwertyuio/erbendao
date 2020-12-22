package com.qf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
@ExcelTarget("users")
public class User implements Serializable {

    @Excel(name = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Excel(name = "邮箱",width = 20.0)
    @Column(name = "email")
    private String email;

    @Excel(name = "姓名")
    @Column(name = "user_name")
    private String userName;

    @ExcelIgnore
    private String code;

    @Excel(name = "密码")
    private String password;

    @Excel(name = "会员",replace = {"未激活_0","已激活_1"})
    private Integer members;

    @ExcelIgnore
    private String token;

}
