package com.fixontricky.gomtools.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class GOMModel {
    @Id
    private String username;

    //TODO: encryption
    private String password;

    @ManyToMany(mappedBy = "goms")
    private List<GroupOrderModel> groupOrders = new ArrayList<>();
}