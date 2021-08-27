package com.imunizacao.vacina.model.dto;

import com.imunizacao.vacina.model.entities.Permission;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class PermissionDTO extends RepresentationModel<PermissionDTO> implements Serializable {

    private Long id;
    private String description;

    public PermissionDTO(){}

    public PermissionDTO(Long id, String description){
        this.id = id;
        this.description = description;
    }

    public PermissionDTO(Permission permission){
        id = permission.getId();
        description = permission.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
