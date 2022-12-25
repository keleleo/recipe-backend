package com.dev.backend.repositories;

import com.dev.backend.enums.PermissionName;
import com.dev.backend.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    List<Permission> findByNameIn(List<PermissionName> permissionNames);
    List<Permission> findByName(PermissionName Name);
}
