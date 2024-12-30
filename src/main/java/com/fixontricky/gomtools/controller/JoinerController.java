package com.fixontricky.gomtools.controller;

import com.fixontricky.gomtools.DTO.JoinerDTO;
import com.fixontricky.gomtools.service.JoinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joiners")
public class JoinerController {
    @Autowired
    private JoinerService joinerService;

    @GetMapping
    public ResponseEntity<List<JoinerDTO>> getAllJoiners() {
        List<JoinerDTO> joiners = joinerService.getAll();
        return ResponseEntity.ok(joiners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoinerDTO> getJoinerById(@PathVariable int id) {
        JoinerDTO joiner = joinerService.getById(id);
        return ResponseEntity.ok(joiner);
    }

    @PostMapping
    public ResponseEntity<JoinerDTO> createJoiner(@RequestBody JoinerDTO joinerDTO) {
        JoinerDTO createdJoiner = joinerService.create(joinerDTO);
        return ResponseEntity.status(201).body(createdJoiner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JoinerDTO> updateJoiner(@PathVariable Long id, @RequestBody JoinerDTO joinerDTO) {
        JoinerDTO updatedJoiner = joinerService.update(id, joinerDTO);
        return ResponseEntity.ok(updatedJoiner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoiner(@PathVariable int id) {
        joinerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}