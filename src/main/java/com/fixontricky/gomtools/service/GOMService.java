package com.fixontricky.gomtools.service;

import com.fixontricky.gomtools.model.GOMModel;
import com.fixontricky.gomtools.repository.GOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GOMService {
    @Autowired
    private GOMRepository gomRepository;

    public GOMModel create(String username, String password) {
        GOMModel gom = new GOMModel();
        gom.setUsername(username);
        gom.setPassword(password);
        return gomRepository.save(gom);
    }

    public GOMModel update(String username, GOMModel gom) {
        if (gomRepository.existsById(username)) {
            gom.setUsername(username);
            return gomRepository.save(gom);
        }
        return null;
    }

    public void delete(String username) {
        gomRepository.deleteById(username);
    }

    public GOMModel login(String username, String password) {
        return gomRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}