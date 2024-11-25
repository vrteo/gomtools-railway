package com.fixontricky.gomtools.repository;

import com.fixontricky.gomtools.model.GOMModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GOMRepository extends JpaRepository<GOMModel, String> {
    Optional<GOMModel> findByUsernameAndPassword(String username, String password);
}