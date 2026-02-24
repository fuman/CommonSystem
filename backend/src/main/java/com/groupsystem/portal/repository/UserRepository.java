package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.PortalUser;
import com.groupsystem.portal.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<PortalUser, Long> {
    List<PortalUser> findByUserType(UserType userType);
}
