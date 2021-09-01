package com.imunizacao.vacina.controllers;

import com.imunizacao.vacina.model.dto.DoseDTO;
import com.imunizacao.vacina.services.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    private DoseService doseService;

    @Autowired
    private PagedResourcesAssembler<DoseDTO> assembler;

    @GetMapping("/id{}")
    public DoseDTO findById(@PathVariable("id") Long id) throws Exception{
        var entity = doseService.findById(id);
        addHateOS(entity);
        return entity;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "doseDate"));

        var entityList = doseService.findAll(pageable);

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    private void addHateOS(DoseDTO dto) throws Exception {
        dto.add(linkTo(methodOn(DoseController.class).findById(dto.getId())).withSelfRel());
    }
}
