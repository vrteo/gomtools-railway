package com.fixontricky.gomtools.controller;

import com.fixontricky.gomtools.DTO.JoinerDTO;
import com.fixontricky.gomtools.model.JoinerModel;
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
    public List<JoinerDTO> getAll() {
        return joinerService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<JoinerDTO> getById(@RequestParam int id) {
        return ResponseEntity.ok(joinerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<JoinerDTO> createJoiner(@RequestBody JoinerDTO dto) {
        return ResponseEntity.ok(joinerService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<JoinerDTO> updateJoiner(@RequestParam int id, @RequestBody JoinerDTO joinerDTO) {
        JoinerDTO updatedJoiner = joinerService.update(id, joinerDTO);
        if (updatedJoiner != null) {
            return ResponseEntity.ok(updatedJoiner);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoiner(@PathVariable int id) {
        joinerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}