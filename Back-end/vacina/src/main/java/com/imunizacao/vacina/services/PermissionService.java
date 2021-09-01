package com.imunizacao.vacina.services;

import com.imunizacao.vacina.exception.ResourceAlreadyExists;
import com.imunizacao.vacina.exception.ResourceNotFound;
import com.imunizacao.vacina.model.entities.Permission;
import com.imunizacao.vacina.model.dto.PermissionDTO;
import com.imunizacao.vacina.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionDTO create(PermissionDTO permission) {
        if (permissionRepository.findByDescription(permission.getDescription().toUpperCase()) != null) {
            throw new ResourceAlreadyExists("Permission " + permission.getDescription() + " already exists");
        }

        return new PermissionDTO(permissionRepository.save(new Permission(permission)));
    }

    public PermissionDTO findById(Long id) {
        var entity = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Permission not found"));
        return new PermissionDTO(entity);
    }

    public PermissionDTO findByDescription(String description) {
        var entity = permissionRepository.findByDescription(description);
        if (entity == null){
            throw new ResourceNotFound("Permission not found");
        }
        return new PermissionDTO(entity);
    }

    public Page<PermissionDTO> findAll(Pageable pageable) {
        var entityList = permissionRepository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    private PermissionDTO convertToDTO(Permission permission) {
        return new PermissionDTO(permission);
    }
}
