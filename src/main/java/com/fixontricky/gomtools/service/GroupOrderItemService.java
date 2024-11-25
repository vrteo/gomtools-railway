package com.fixontricky.gomtools.service;

import com.fixontricky.gomtools.model.GroupOrderItemModel;
import com.fixontricky.gomtools.repository.GroupOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupOrderItemService {
    @Autowired
    private GroupOrderItemRepository groupOrderItemRepository;

    public List<GroupOrderItemModel> getAll() {
        return groupOrderItemRepository.findAll();
    }

    public Optional<GroupOrderItemModel> getById(int id) {
        return groupOrderItemRepository.findById(id);
    }

    public GroupOrderItemModel create(GroupOrderItemModel groupOrderItem) {
        return groupOrderItemRepository.save(groupOrderItem);
    }

    public GroupOrderItemModel update(int id, GroupOrderItemModel groupOrderItem) {
        if(groupOrderItemRepository.existsById(id)) {
            groupOrderItem.setId(id);
            return groupOrderItemRepository.save(groupOrderItem);
        }
        return null;
    }

    public void delete(int id) {
        groupOrderItemRepository.deleteById(id);
    }
}
