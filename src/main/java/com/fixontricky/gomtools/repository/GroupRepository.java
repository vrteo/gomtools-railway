package com.fixontricky.gomtools.repository;

import com.fixontricky.gomtools.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Integer> {
    Optional<GroupModel> findGroupModelByGroupName(String name);
    
}