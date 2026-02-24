package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
    List<ProgramEntity> findBySubsystemId(Long subsystemId);
}
