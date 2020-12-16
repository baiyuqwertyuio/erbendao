package com.qf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movie")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double score;

    private String url;
    @Column(name = "big_pic")
    private String bigPic;

    private String pic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String role;

    @Column(name = "c_id")
    private Integer cid;

    @Column(name = "s_id")
    private Integer sid;

    private Integer vip;

    private String summary;

}
