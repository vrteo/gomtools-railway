package com.fixontricky.gomtools.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class GroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<MemberModel> members = new ArrayList<>();

    @OneToOne(mappedBy = "group", optional = false)
    private GroupOrderModel groupOrder;
}