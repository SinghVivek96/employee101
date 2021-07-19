package com.vivek.employee101.security;

public enum ApplicationUserPermission {
    EMPLOYEE_READ("random:read"),
    EMPLOYEE_WRITE("random:write"),
    ADMIN_READ("random:read"),
    ADMIN_WRITE("random:write");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
