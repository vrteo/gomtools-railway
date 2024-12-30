package com.fixontricky.gomtools.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class GroupOrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private GroupModel group;

    private String name;

    // Metadata
    private Instant dateCreated = Instant.now();
    private Instant dateLastUpdated;
    private String updateReason;

    // Items
    @OneToMany(mappedBy = "groupOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupOrderItemModel> goItems = new ArrayList<>();
}