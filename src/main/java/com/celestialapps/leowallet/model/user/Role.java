package com.celestialapps.leowallet.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role implements GrantedAuthority {

    USER, ADMIN, TEMP;

    @Override
    public String getAuthority() {
        return name();
    }

    public static SimpleGrantedAuthority getRole(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }

    @Override
    public String toString() {
        return name();
    }

    public String getRole(){
        return "ROLE_" + this.name();
    }
}
