package com.fixontricky.gomtools.service;

import com.fixontricky.gomtools.DTO.JoinerDTO;
import com.fixontricky.gomtools.exceptions.BadRequestException;
import com.fixontricky.gomtools.exceptions.ResourceNotFoundException;
import com.fixontricky.gomtools.model.JoinerModel;
import org.hibernate.mapping.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fixontricky.gomtools.repository.JoinerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoinerService {
    private static final Logger logger = LoggerFactory.getLogger(JoinerService.class);

    @Autowired
    private JoinerRepository joinerRepository;

    public List<JoinerDTO> getAll() {
        return joinerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public JoinerDTO getById(int id) {
        return joinerRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Joiner with ID " + id + " not found"));
    }

    public JoinerDTO create(JoinerDTO joiner) {
        if (joiner.getUsername() == null || joiner.getUsername().isEmpty()) {
            throw new BadRequestException("Username cannot be empty");
        }

        JoinerModel joinerModel = new JoinerModel();
        joinerModel.setUsername(joiner.getUsername());
        joinerModel.setPlatform(joiner.getPlatform());

        joiner.setId(toDTO(joinerRepository.save(joinerModel)).getId());
        return joiner;
    }

    public JoinerDTO update(int id, JoinerDTO dto) {
        JoinerModel joiner = joinerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Joiner not found with ID: " + id));

        joiner.setUsername(dto.getUsername());
        joiner.setPlatform(dto.getPlatform());

        JoinerModel updatedJoiner = joinerRepository.save(joiner);
        return toDTO(updatedJoiner);
    }

    public void delete(int id) {
        joinerRepository.deleteById(id);
    }

    private JoinerDTO toDTO(JoinerModel joiner) {
        JoinerDTO dto = new JoinerDTO();
        dto.setId(joiner.getId());
        dto.setPlatform(joiner.getPlatform());
        dto.setUsername(joiner.getUsername());
        return dto;
    }

    private JoinerModel toModel(JoinerDTO dto) {
        JoinerModel model = new JoinerModel();
        model.setUsername(dto.getUsername());
        model.setPlatform(dto.getPlatform());
        model.setId(dto.getId());
        return model;
    }
}