package com.qf.pojo;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    private String userName;

    private String code;

    private String password;

}
