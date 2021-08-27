package com.imunizacao.vacina.repositories;

import com.imunizacao.vacina.model.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByDescription(String description);
}
