package com.groupsystem.portal.repository;

import com.groupsystem.portal.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByProgramId(Long programId);
}
