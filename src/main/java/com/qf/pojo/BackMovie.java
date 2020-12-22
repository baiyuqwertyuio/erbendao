package com.qf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class BackMovie {

    private Integer id;

    private String name;

    private Double score;

    private String url;

    private String bigPic;

    private String pic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String role;

    private String cat;

    private String type;

    private Integer vip;

    private String summary;

}
