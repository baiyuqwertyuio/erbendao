package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "catalog")
@Data
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
