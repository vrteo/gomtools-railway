package com.fixontricky.gomtools.model;

import com.fixontricky.gomtools.enums.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class JoinerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @OneToMany(mappedBy = "joiner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupOrderItemModel> items = new ArrayList<>();
}