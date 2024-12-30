package com.fixontricky.gomtools.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupDTO {
    private int id;
    private String name;
    private List<String> members = new ArrayList<>();
}
