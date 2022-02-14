package com.batiaev.provisioner.model.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    MASTER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
