package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "seconde")
@Data
public class Seconde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @Column(name = "c_id")
    private Integer cid;
}
