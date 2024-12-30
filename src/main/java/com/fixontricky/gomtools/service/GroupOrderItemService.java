package com.fixontricky.gomtools.service;

import com.fixontricky.gomtools.DTO.GOItemDTO;
import com.fixontricky.gomtools.enums.PaymentStatus;
import com.fixontricky.gomtools.enums.Status;
import com.fixontricky.gomtools.exceptions.ResourceNotFoundException;
import com.fixontricky.gomtools.model.GroupOrderItemModel;
import com.fixontricky.gomtools.repository.GroupOrderItemRepository;
import com.fixontricky.gomtools.repository.GroupOrderRepository;
import com.fixontricky.gomtools.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupOrderItemService {
    @Autowired
    private GroupOrderItemRepository groupOrderItemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GroupOrderRepository groupOrderRepository;

    public List<GOItemDTO> getAll() {
        return groupOrderItemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<GOItemDTO> getById(int id) {
        return groupOrderItemRepository.findById(id).map(this::toDTO);
    }

    public GOItemDTO create(GOItemDTO goItemDTO) {
        GroupOrderItemModel groupOrderItem = toModel(goItemDTO);
        GroupOrderItemModel savedItem = groupOrderItemRepository.save(groupOrderItem);
        return toDTO(savedItem);
    }

    public GOItemDTO update(int id, GOItemDTO goItemDTO) {
        if(groupOrderItemRepository.existsById(id)) {
            GroupOrderItemModel groupOrderItem = toModel(goItemDTO);
            groupOrderItem.setId(id);
            GroupOrderItemModel savedItem = groupOrderItemRepository.save(groupOrderItem);
            return toDTO(savedItem);
        }
        throw new ResourceNotFoundException("GroupOrderItem with ID " + id + " not found.");
    }

    public void delete(int id) {
        groupOrderItemRepository.deleteById(id);
    }

    private GOItemDTO toDTO(GroupOrderItemModel model) {
        GOItemDTO dto = new GOItemDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setStoreName(model.getGroupOrder().getName());
        dto.setPrice(model.getPrice());
        dto.setMember(model.getMember().getName());
        dto.setStatus(model.getStatus().name());
        dto.setPaymentStatus(model.getPaymentStatus().name());
        dto.setParentGO(GroupOrderService.toDTO(model.getGroupOrder()));

        return dto;
    }

    public GroupOrderItemModel toModel(GOItemDTO dto) {
        if (dto == null) {
            return null;
        }

        GroupOrderItemModel model = new GroupOrderItemModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setPrice(dto.getPrice());
        memberRepository.getByNameEqualsIgnoreCase(dto.getMember()).ifPresent(model::setMember);
        model.setStatus(Status.valueOf(dto.getStatus()));
        model.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()));
        groupOrderRepository.findById(dto.getParentGO().getId()).ifPresent(model::setGroupOrder);
        return model;
    }
}
