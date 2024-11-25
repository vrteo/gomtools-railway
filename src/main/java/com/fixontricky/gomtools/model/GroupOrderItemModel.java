package com.fixontricky.gomtools.model;

import com.fixontricky.gomtools.enums.PaymentStatus;
import com.fixontricky.gomtools.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GroupOrderItemModel {

    @Id
    private int id;
    private String name;
    private int setNumber;
    private double price;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    private GroupOrderModel groupOrder;

    @ManyToOne
    private MemberModel member;

    @ManyToOne
    private JoinerModel joiner;
}