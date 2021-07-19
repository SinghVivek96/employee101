package com.vivek.employee101.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    EMPLOYEE(Sets.newHashSet(Sets.newHashSet(ApplicationUserPermission.EMPLOYEE_READ,
            ApplicationUserPermission.EMPLOYEE_WRITE))),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.ADMIN_READ,
            ApplicationUserPermission.ADMIN_WRITE));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }
}
