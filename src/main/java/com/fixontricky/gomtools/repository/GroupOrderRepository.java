package com.fixontricky.gomtools.repository;

import com.fixontricky.gomtools.model.GroupOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrderModel, Integer> {

}