package com.groupsystem.portal.service;

import com.groupsystem.portal.domain.*;
import com.groupsystem.portal.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntityLookupService {
    private final ApplicationRepository applicationRepository;
    private final SubsystemRepository subsystemRepository;
    private final ProgramRepository programRepository;
    private final MenuRepository menuRepository;
    private final ApiRepository apiRepository;
    private final PermissionRepository permissionRepository;

    public EntityLookupService(ApplicationRepository applicationRepository, SubsystemRepository subsystemRepository,
                               ProgramRepository programRepository, MenuRepository menuRepository,
                               ApiRepository apiRepository, PermissionRepository permissionRepository) {
        this.applicationRepository = applicationRepository;
        this.subsystemRepository = subsystemRepository;
        this.programRepository = programRepository;
        this.menuRepository = menuRepository;
        this.apiRepository = apiRepository;
        this.permissionRepository = permissionRepository;
    }

    public ApplicationEntity app(Long id) { return applicationRepository.findById(id).orElseThrow(() -> notFound("application", id)); }
    public Subsystem subsystem(Long id) { return subsystemRepository.findById(id).orElseThrow(() -> notFound("subsystem", id)); }
    public ProgramEntity program(Long id) { return programRepository.findById(id).orElseThrow(() -> notFound("program", id)); }
    public Menu menu(Long id) { return menuRepository.findById(id).orElseThrow(() -> notFound("menu", id)); }
    public ApiEndpoint api(Long id) { return apiRepository.findById(id).orElseThrow(() -> notFound("api", id)); }
    public AccessPermission permission(Long id) { return permissionRepository.findById(id).orElseThrow(() -> notFound("permission", id)); }

    private ResponseStatusException notFound(String type, Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, type + " not found: " + id);
    }
}
