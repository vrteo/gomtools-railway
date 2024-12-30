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

    @ManyToOne(optional = false)
    private GroupOrderModel groupOrder;

    @ManyToOne(optional = false)
    private JoinerModel joiner;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String name;

    private double price;

    @ManyToOne (optional = true)
    private MemberModel member;

}