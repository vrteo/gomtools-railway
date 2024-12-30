package com.fixontricky.gomtools.DTO;

import lombok.Data;

@Data
public class GOItemDTO {
    private int id;
    private String name;
    private String storeName;
    private double price;
    private String member;
    private String status;
    private String paymentStatus;
    private GODTO parentGO;
}
