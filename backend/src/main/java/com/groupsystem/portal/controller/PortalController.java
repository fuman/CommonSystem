package com.groupsystem.portal.controller;

import com.groupsystem.portal.domain.*;
import com.groupsystem.portal.dto.Requests.*;
import com.groupsystem.portal.repository.*;
import com.groupsystem.portal.service.EntityLookupService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PortalController {
    private final ApplicationRepository applicationRepository;
    private final SubsystemRepository subsystemRepository;
    private final ProgramRepository programRepository;
    private final MenuRepository menuRepository;
    private final ApiRepository apiRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final EntityLookupService lookup;

    public PortalController(ApplicationRepository applicationRepository, SubsystemRepository subsystemRepository,
                            ProgramRepository programRepository, MenuRepository menuRepository,
                            ApiRepository apiRepository, PermissionRepository permissionRepository,
                            UserRepository userRepository, EntityLookupService lookup) {
        this.applicationRepository = applicationRepository;
        this.subsystemRepository = subsystemRepository;
        this.programRepository = programRepository;
        this.menuRepository = menuRepository;
        this.apiRepository = apiRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.lookup = lookup;
    }

    @GetMapping("/applications")
    public List<ApplicationEntity> applications() { return applicationRepository.findAll(); }
    @PostMapping("/applications")
    public ApplicationEntity createApplication(@RequestBody ApplicationRequest req) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setName(req.name());
        entity.setDescription(req.description());
        return applicationRepository.save(entity);
    }

    @GetMapping("/subsystems")
    public List<Subsystem> subsystems(@RequestParam(required = false) Long applicationId) {
        return applicationId == null ? subsystemRepository.findAll() : subsystemRepository.findByApplicationId(applicationId);
    }
    @PostMapping("/subsystems")
    public Subsystem createSubsystem(@RequestBody SubsystemRequest req) {
        Subsystem s = new Subsystem();
        s.setName(req.name());
        s.setApplication(lookup.app(req.applicationId()));
        return subsystemRepository.save(s);
    }

    @GetMapping("/programs")
    public List<ProgramEntity> programs(@RequestParam(required = false) Long subsystemId) {
        return subsystemId == null ? programRepository.findAll() : programRepository.findBySubsystemId(subsystemId);
    }
    @PostMapping("/programs")
    public ProgramEntity createProgram(@RequestBody ProgramRequest req) {
        ProgramEntity p = new ProgramEntity();
        p.setName(req.name());
        p.setSubsystem(lookup.subsystem(req.subsystemId()));
        return programRepository.save(p);
    }

    @GetMapping("/menus")
    public List<Menu> menus(@RequestParam(required = false) Long programId) {
        return programId == null ? menuRepository.findAll() : menuRepository.findByProgramId(programId);
    }
    @PostMapping("/menus")
    public Menu createMenu(@RequestBody MenuRequest req) {
        Menu m = new Menu();
        m.setName(req.name());
        m.setProgram(lookup.program(req.programId()));
        return menuRepository.save(m);
    }

    @GetMapping("/apis")
    public List<ApiEndpoint> apis(@RequestParam(required = false) Long programId) {
        return programId == null ? apiRepository.findAll() : apiRepository.findByProgramId(programId);
    }
    @PostMapping("/apis")
    public ApiEndpoint createApi(@RequestBody ApiRequest req) {
        ApiEndpoint a = new ApiEndpoint();
        a.setName(req.name());
        a.setPath(req.path());
        a.setMethod(req.method());
        a.setProgram(lookup.program(req.programId()));
        return apiRepository.save(a);
    }

    @GetMapping("/permissions")
    public List<AccessPermission> permissions() { return permissionRepository.findAll(); }
    @PostMapping("/permissions")
    public AccessPermission createPermission(@RequestBody PermissionRequest req) {
        AccessPermission p = new AccessPermission();
        p.setCode(req.code());
        p.setName(req.name());
        if (req.menuId() != null) p.setMenu(lookup.menu(req.menuId()));
        if (req.apiId() != null) p.setApi(lookup.api(req.apiId()));
        return permissionRepository.save(p);
    }

    @GetMapping("/users")
    public List<PortalUser> users(@RequestParam(required = false) UserType userType) {
        return userType == null ? userRepository.findAll() : userRepository.findByUserType(userType);
    }
    @PostMapping("/users")
    public PortalUser createUser(@RequestBody UserRequest req) {
        PortalUser u = new PortalUser();
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setUserType(req.userType());
        Set<AccessPermission> perms = new HashSet<>();
        if (req.permissionIds() != null) {
            req.permissionIds().forEach(id -> perms.add(lookup.permission(id)));
        }
        u.setPermissions(perms);
        return userRepository.save(u);
    }
}
