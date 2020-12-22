package com.qf.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movie")
@Data
@ExcelTarget("movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel(name = "编号")
    private Integer id;

    @Excel(name = "名称")
    private String name;

    @Excel(name = "评分")
    private Double score;

    @ExcelIgnore
    private String url;

    @ExcelIgnore
    @Column(name = "big_pic")
    private String bigPic;

    @Excel(name = "图片",type = 2,width = 22,height = 22)
    private String pic;

    @Excel(name = "日期",format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ExcelIgnore
    private String role;

    @ExcelIgnore
    @Column(name = "c_id")
    private Integer cid;

    @ExcelIgnore
    @Column(name = "s_id")
    private Integer sid;

    @Excel(name = "会员影片",replace = {"是_1","否_0"})
    private Integer vip;

    @ExcelIgnore
    private String summary;

}
