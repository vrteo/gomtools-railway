package com.fixontricky.gomtools.controller;

import com.fixontricky.gomtools.DTO.GODTO;
import com.fixontricky.gomtools.service.GroupOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gos")
public class GOController {
    private static final Logger logger = LoggerFactory.getLogger(GOController.class);

    @Autowired
    private GroupOrderService goService;

    @GetMapping
    public List<GODTO> getAll() {
        return goService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<GODTO> getById(@RequestParam int id) {
        return ResponseEntity.ok(goService.getById(id));
    }

//    @PostMapping
//    public ResponseEntity<GODTO> createJoiner(@RequestBody GODTO dto) {
//        return ResponseEntity.ok(goService.create(dto));
//    }
}