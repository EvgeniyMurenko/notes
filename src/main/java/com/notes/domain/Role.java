package com.notes.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mr_je on 19.06.2018.
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
