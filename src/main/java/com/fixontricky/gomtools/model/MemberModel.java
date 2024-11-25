package com.fixontricky.gomtools.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class MemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne(optional = false)
    private GroupModel group;
    
    @OneToMany(mappedBy = "member")
    private Collection<GroupOrderItemModel> goItems;
}