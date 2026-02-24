package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.AccessPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<AccessPermission, Long> {
}
