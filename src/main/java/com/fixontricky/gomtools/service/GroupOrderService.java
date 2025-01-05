package com.fixontricky.gomtools.service;

import com.fixontricky.gomtools.DTO.GODTO;
import com.fixontricky.gomtools.exceptions.ResourceNotFoundException;
import com.fixontricky.gomtools.model.GOMModel;
import com.fixontricky.gomtools.model.GroupModel;
import com.fixontricky.gomtools.model.GroupOrderModel;
import com.fixontricky.gomtools.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupOrderService {
    private static final Logger logger = LoggerFactory.getLogger(GroupOrderService.class);
    @Autowired
    private GroupOrderRepository groupOrderRepository;
    @Autowired
    private GroupOrderItemRepository groupOrderItemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GOMRepository gomRepository;

    public List<GODTO> getAll() {
        return groupOrderRepository.findAll().stream()
                .map(GroupOrderService::toDTO)
                .collect(Collectors.toList());
    }

    public GODTO getById(int id) {
        return groupOrderRepository.findById(id)
                .map(GroupOrderService::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Group Order with ID " + id + "not found"));
    }

    public static GODTO toDTO(GroupOrderModel model) {
        if (model == null) {
            throw new IllegalArgumentException("GroupOrderModel is null and cannot be converted to GODTO.");
        }
        GODTO dto = new GODTO();
        dto.setId(model.getId());
        dto.setGroupName(model.getGroup().getGroupName());
        dto.setName(model.getName());
        dto.setDateCreated(model.getDateCreated());
        dto.setDateLastUpdated(model.getDateLastUpdated());
        if (model.getGoms() != null && !model.getGoms().isEmpty()) {
            dto.setGomNames(model.getGoms().stream().map(GOMModel::getUsername).toList());
        }

        return dto;
    }

    public GODTO update(int id, GODTO godto) {
        GroupOrderModel groupOrder = toModel(godto);
        if (groupOrderRepository.existsById(id)) {
            groupOrder.setId(id);
            return toDTO(groupOrderRepository.save(groupOrder));
        } else {
            throw new ResourceNotFoundException("Group Order with ID " + id + " not found");
        }
    }

    public void delete(int id) {
        if (!groupOrderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Group Order with ID " + id + " not found");
        }
        groupOrderRepository.deleteById(id);
    }

    public GODTO create(GODTO godto) {
        try {
            logger.info("Creating group order model for store: {}.", godto.getName());

            GroupOrderModel groupOrder = new GroupOrderModel();
            groupOrder.setName(godto.getName());
            GroupModel group = groupRepository.findGroupModelByGroupName(godto.getGroupName()).orElse(null);
            if (godto.getGomNames() != null && !godto.getGomNames().isEmpty()) {
                groupOrder.setGoms(gomRepository.findByUsernameIn(godto.getGomNames()).stream().toList());
            }

            logger.info("Group with name {}: {}.", godto.getGroupName(), group);

            groupOrder.setGroup(group);
            groupOrder.setDateLastUpdated(Instant.now());
            groupOrderRepository.save(groupOrder);

            logger.info("Group order with name {} saved successfully.", godto.getName());

            return toDTO(groupOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public GroupOrderModel toModel(GODTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("GODTO is null and cannot be converted to GroupOrderModel.");
        }
        GroupOrderModel model = new GroupOrderModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDateCreated(dto.getDateCreated());
        model.setDateLastUpdated(dto.getDateLastUpdated());
        GroupModel group = new GroupModel();
        group.setGroupName(dto.getGroupName());
        model.setGroup(group);
        if (dto.getGomNames() != null && !dto.getGomNames().isEmpty()) {
            model.setGoms(gomRepository.findByUsernameIn(dto.getGomNames()).stream().toList());
        }

        return model;
    }
}
