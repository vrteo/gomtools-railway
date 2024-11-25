package com.fixontricky.gomtools.controller;

import com.fixontricky.gomtools.model.GOMModel;
import com.fixontricky.gomtools.service.GOMService;
import com.fixontricky.gomtools.service.JoinerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goms")
public class GomController {
    private static final Logger logger = LoggerFactory.getLogger(GomController.class);

    @Autowired
    private GOMService gomService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        GOMModel gom = gomService.login(username, password);
        if (gom != null) {
            return ResponseEntity.ok(gom.getUsername());
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/register")
    public ResponseEntity<GOMModel> register(@RequestParam String username, @RequestParam String password) {
        GOMModel gom = gomService.create(username, password);
        if (gom != null) {
            return ResponseEntity.ok(gom);
        }
        return ResponseEntity.status(401).build();
    }

}