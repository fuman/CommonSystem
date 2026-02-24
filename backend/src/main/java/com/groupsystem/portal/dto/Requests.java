package com.groupsystem.portal.dto;

import com.groupsystem.portal.domain.UserType;

import java.util.Set;

public class Requests {
    public record ApplicationRequest(String name, String description) {}
    public record SubsystemRequest(String name, Long applicationId) {}
    public record ProgramRequest(String name, Long subsystemId) {}
    public record MenuRequest(String name, Long programId) {}
    public record ApiRequest(String name, String path, String method, Long programId) {}
    public record PermissionRequest(String code, String name, Long menuId, Long apiId) {}
    public record UserRequest(String username, String email, UserType userType, Set<Long> permissionIds) {}
}
