package com.fixontricky.gomtools.repository;

import com.fixontricky.gomtools.model.GroupModel;
import com.fixontricky.gomtools.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberModel, Integer> {
    public List<MemberModel> findAllByGroup(GroupModel group);
}