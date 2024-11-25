package com.fixontricky.gomtools.service;

import com.fixontricky.gomtools.enums.PaymentStatus;
import com.fixontricky.gomtools.enums.Status;
import com.fixontricky.gomtools.model.*;
import com.fixontricky.gomtools.repository.GroupOrderItemRepository;
import com.fixontricky.gomtools.repository.GroupOrderRepository;
import com.fixontricky.gomtools.repository.GroupRepository;
import com.fixontricky.gomtools.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<GroupOrderModel> getAll() {
        return groupOrderRepository.findAll();
    }

    public Optional<GroupOrderModel> getById(int id) {
        return groupOrderRepository.findById(id);
    }

    public GroupOrderModel create(String storeName, int groupId) {
        try {
            logger.info("Creating group order model for store: {}.", storeName);

            GroupOrderModel groupOrder = new GroupOrderModel();
            groupOrder.setStoreName(storeName);
            GroupModel group = groupRepository.findById(groupId).orElse(null);

            logger.info("Group with id {}: {}.", groupId, group);

            groupOrder.setGroup(group);
            groupOrderRepository.save(groupOrder);

            logger.info("Group order with name {} saved successfully.", storeName);

            return groupOrder;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public void addSet(int goId) {
        try {
            // find the group order
            Optional<GroupOrderModel> groupOrder = groupOrderRepository.findById(goId);
            if (groupOrder.isEmpty()) {
                logger.error("Group order with ID {} not found.", goId);
                return;
            }
            logger.info("Group order with ID {} is {}.", goId, groupOrder.get().getStoreName());

            List<MemberModel> members = memberRepository.findAllByGroup(groupOrder.get().getGroup());

            int setNumber = groupOrder.get().getNrOfSets();

            for (MemberModel member : members) {
                GroupOrderItemModel item = new GroupOrderItemModel();
                item.setGroupOrder(groupOrder.get());
                item.setSetNumber(setNumber + 1);
                item.setMember(member);
                item.setName(member.getName());
                item.setPaymentStatus(PaymentStatus.PENDING_INITIALS);
                item.setStatus(Status.UNAVAILABLE);
                groupOrderItemRepository.save(item);
                logger.info("Added group order item: {}.", item);
            }

            groupOrder.get().setNrOfSets(setNumber + 1);
            groupOrderRepository.save(groupOrder.get());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void removeSet(int goId, int setNumber) {
        // TODO: THIS ONLY WORKS IF REMOVING LAST SET. DO NOT TRY TO REMOVE OTHER SETS, IT WILL BREAK. FIX THIS FOR FULL RELEASE
        try {
            // find the group order
            Optional<GroupOrderModel> groupOrder = groupOrderRepository.findById(goId);
            if (groupOrder.isEmpty()) {
                return;
            }
  
            groupOrderItemRepository.removeAllBySetNumber(setNumber);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public GroupOrderModel update(int id, GroupOrderModel groupOrder) {
        if (groupOrderRepository.existsById(id)) {
            groupOrder.setId(id);
            return groupOrderRepository.save(groupOrder);
        }
        return null;
    }

    public void delete(int id) {
        groupOrderRepository.deleteById(id);
    }


}
