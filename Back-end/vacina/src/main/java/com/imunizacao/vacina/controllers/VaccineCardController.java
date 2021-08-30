package com.imunizacao.vacina.controllers;

import com.imunizacao.vacina.model.dto.VaccineCardDTO;
import com.imunizacao.vacina.services.VaccineCardService;
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
@RequestMapping("/vaccineCard")
public class VaccineCardController {

    @Autowired
    private VaccineCardService vaccineService;

    @Autowired
    private PagedResourcesAssembler<VaccineCardDTO> assembler;

    @PostMapping
    public VaccineCardDTO create(@RequestBody VaccineCardDTO dto) throws Exception {
        var entity = vaccineService.create(dto);

        entity.add(linkTo(methodOn(VaccineCardController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}")
    public VaccineCardDTO findById(@PathVariable("id") Long id) {
        var entity = vaccineService.findById(id);

        entity.add(linkTo(methodOn(VaccineCardService.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "person"));

        var entityList = vaccineService.findAll(pageable);

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }


}
