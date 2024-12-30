package com.fixontricky.gomtools.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GOMModel {
    @Id
    private String username;

    //TODO: encryption
    private String password;
}