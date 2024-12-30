package com.fixontricky.gomtools.repository;

import com.fixontricky.gomtools.model.JoinerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinerRepository extends JpaRepository<JoinerModel, Integer> {
    
}