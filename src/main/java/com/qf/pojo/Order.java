package com.qf.pojo;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private double price;
    private String name;
    private String transferId;
    private Integer uid;
}
