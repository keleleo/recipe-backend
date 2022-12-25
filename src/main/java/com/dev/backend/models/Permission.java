package com.dev.backend.models;

import com.dev.backend.enums.PermissionName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PermissionName name;

    public Permission() {
    }

    public Permission(PermissionName name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name.name();
    }

}
