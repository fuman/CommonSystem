package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.ApiEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiRepository extends JpaRepository<ApiEndpoint, Long> {
    List<ApiEndpoint> findByProgramId(Long programId);
}
