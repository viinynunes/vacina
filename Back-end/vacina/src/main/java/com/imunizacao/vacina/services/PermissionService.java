package com.imunizacao.vacina.services;

import com.imunizacao.vacina.model.Permission;
import com.imunizacao.vacina.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission create(Permission permission) throws Exception{
        if (permissionRepository.findByDescription(permission.getDescription()) != null){
            throw new Exception("Permission " + permission.getDescription() + " already exists");
        }

        return permissionRepository.save(permission);
    }

    public Permission findById(Long id){
        return permissionRepository.findById(id).orElseThrow();
    }

    public Permission findByDescription(String description){
        return permissionRepository.findByDescription(description);
    }

    public Page<Permission> findAll(Pageable pageable){
        return permissionRepository.findAll(pageable);
    }
}
