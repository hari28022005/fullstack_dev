package com.example.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.model.Permission.*;


@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_READ,
                    EMPLOYEE_CREATE,
                    EMPLOYEE_UPDATE,
                    EMPLOYEE_DELETE,
                    EMPLOYEE_READ
                    )
    ),
    EMPLOYEE(
            Set.of(
                    EMPLOYEE_CREATE,
                    EMPLOYEE_UPDATE,
                    EMPLOYEE_DELETE,
                    EMPLOYEE_READ
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
