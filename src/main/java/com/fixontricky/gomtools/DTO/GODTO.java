package com.fixontricky.gomtools.DTO;

import lombok.Data;

import java.time.Instant;

@Data
public class GODTO {
    private int id;
    private String groupName;
    private String storeName;
    private String gomNames;
    private Instant dateCreated;
    private Instant dateLastUpdated;
}