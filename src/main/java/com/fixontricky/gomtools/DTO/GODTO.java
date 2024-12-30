package com.fixontricky.gomtools.DTO;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class GODTO {
    private int id;
    private String groupName;
    private String name;
    private List<String> gomNames = new ArrayList<>();
    private Instant dateCreated;
    private Instant dateLastUpdated;
    private String updateReason;
}