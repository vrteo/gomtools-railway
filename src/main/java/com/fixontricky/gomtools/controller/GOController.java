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
    
    @PostMapping
    public ResponseEntity<GODTO> createGO(@RequestBody GODTO goDto) {
        logger.info("Creating new GO: {}", goDto);
        GODTO createdGO = goService.create(goDto);
        return ResponseEntity.ok(createdGO);
    }

    @GetMapping
    public ResponseEntity<List<GODTO>> getGOs() {
        logger.info("Fetching all GOs");
        List<GODTO> gos = goService.getAll();
        return ResponseEntity.ok(gos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GODTO> getGOById(@PathVariable int id) {
        logger.info("Fetching GO with ID: {}", id);
        GODTO go = goService.getById(id);
        return ResponseEntity.ok(go);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GODTO> updateGO(@PathVariable int id, @RequestBody GODTO goDto) {
        logger.info("Updating GO with ID: {}", id);
        GODTO updatedGO = goService.update(id, goDto);
        return ResponseEntity.ok(updatedGO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGO(@PathVariable int id) {
        logger.info("Deleting GO with ID: {}", id);
        goService.delete(id);
        return ResponseEntity.noContent().build();
    }
}