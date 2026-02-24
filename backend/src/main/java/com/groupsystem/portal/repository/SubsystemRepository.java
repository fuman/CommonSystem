package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.Subsystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubsystemRepository extends JpaRepository<Subsystem, Long> {
    List<Subsystem> findByApplicationId(Long applicationId);
}
