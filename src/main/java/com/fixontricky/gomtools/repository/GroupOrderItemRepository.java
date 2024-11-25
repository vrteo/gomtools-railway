package com.fixontricky.gomtools.repository;

import com.fixontricky.gomtools.model.GroupOrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GroupOrderItemRepository extends JpaRepository<GroupOrderItemModel, Integer> {
    public void removeAllBySetNumber(int setNumber);
}