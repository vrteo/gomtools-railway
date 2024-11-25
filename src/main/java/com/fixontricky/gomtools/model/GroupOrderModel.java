package com.fixontricky.gomtools.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class GroupOrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private GroupModel group;

    private String storeName;
    private String goms;
    private int nrOfSets = 0;

    @OneToMany(mappedBy = "groupOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupOrderItemModel> items = new ArrayList<>();
}