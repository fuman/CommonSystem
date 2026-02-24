package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
}
