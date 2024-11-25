package com.fixontricky.gomtools.DTO;

import com.fixontricky.gomtools.enums.Platform;
import lombok.Data;

@Data
public class JoinerDTO {
    private int id;
    private String username;
    private Platform platform;
}