package com.imunizacao.vacina.controllers;

import com.imunizacao.vacina.model.dto.PermissionDTO;
import com.imunizacao.vacina.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ApiIgnore
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PagedResourcesAssembler<PermissionDTO> assembler;

    @PostMapping
    public PermissionDTO create(@RequestBody PermissionDTO permission) {
        permission.setDescription(permission.getDescription().toUpperCase());
        var entity = permissionService.create(permission);

        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}")
    public PermissionDTO findById(@PathVariable("id") Long id){
        var entity = permissionService.findById(id);

        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/findByDescription/{description}")
    public PermissionDTO findByDescription(@PathVariable("description") String description){
        var entity = permissionService.findByDescription(description);

        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var entityList = permissionService.findAll(pageable);

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
