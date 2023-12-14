
package com.example.demo.Entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
    //ура пол пятого ночи я иду спать
}
