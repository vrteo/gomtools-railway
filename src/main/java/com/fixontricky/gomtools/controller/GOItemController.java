package com.fixontricky.gomtools.controller;

import com.fixontricky.gomtools.DTO.GOItemDTO;
import com.fixontricky.gomtools.service.GroupOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/goitems")
public class GOItemController {
    @Autowired
    private GroupOrderItemService groupOrderItemService;
    
    
    @GetMapping
    public List<GOItemDTO> getAllGOItems() {
        return groupOrderItemService.getAll();
    }

    @GetMapping("/{id}")
    public GOItemDTO getGOItemById(@PathVariable int id) {
        return groupOrderItemService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "GOItem not found"));
    }

    @PostMapping
    public GOItemDTO createGOItem(@RequestBody GOItemDTO goItem) {
        return groupOrderItemService.create(goItem);
    }

    @PutMapping("/{id}")
    public GOItemDTO updateGOItem(@PathVariable int id, @RequestBody GOItemDTO goItem) {
        return groupOrderItemService.update(id, goItem);
    }

    @DeleteMapping("/{id}")
    public void deleteGOItem(@PathVariable int id) {
        groupOrderItemService.delete(id);
    }
}
