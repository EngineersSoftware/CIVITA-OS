package co.gov.antioquia.civitas.civitas_os.enums;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    USER,
    ADMIN,
    FUNCTIONARY;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }

}
