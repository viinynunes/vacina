package com.imunizacao.vacina.controllers;

import com.imunizacao.vacina.model.dto.DoseDTO;
import com.imunizacao.vacina.model.dto.VaccineCardDTO;
import com.imunizacao.vacina.services.DoseService;
import com.imunizacao.vacina.services.VaccineCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "Vaccine Card")
@RestController
@RequestMapping("/vaccineCard")
public class VaccineCardController {

    @Autowired
    private VaccineCardService vaccineService;

    @Autowired
    private DoseService doseService;

    @Autowired
    private PagedResourcesAssembler<VaccineCardDTO> assembler;

    @ApiOperation(value = "Used to create a new Vaccine Card. Needed to have an user already created")
    @PostMapping
    public VaccineCardDTO create(@RequestBody VaccineCardDTO dto) {
        var entity = vaccineService.create(dto);

        addHateOS(entity);
        return entity;
    }

    @ApiOperation(value = "Used to insert a Dose into vaccine Card. Needed to inform the vaccine card ID and the Dose manufacture and description")
    @PostMapping("/insertDose/{vaccineCardID}")
    public VaccineCardDTO insertDose(@PathVariable("vaccineCardID") Long vaccineID, @RequestBody DoseDTO doseDTO) {

        var entity = vaccineService.insertDose(vaccineID, doseDTO);
        addHateOS(entity);
        return entity;
    }

    @ApiOperation(value = "Used to find a recorded vaccine card searching by id")
    @GetMapping(value = "/{id}")
    public VaccineCardDTO findById(@PathVariable("id") Long id) {
        var entity = vaccineService.findById(id);

        entity.add(linkTo(methodOn(VaccineCardService.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @ApiOperation(value = "Used to find a recorded vaccine card searching by person cpf")
    @GetMapping("/findByPersonCpf/{cpf}")
    public VaccineCardDTO findByPersonCpf(@PathVariable("cpf") String cpf) {
        var entity = vaccineService.findByUserCPF(cpf);
        addHateOS(entity);
        return entity;
    }

    @ApiOperation(value = "Used to find all recorded vaccine card")
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

    private void addHateOS(VaccineCardDTO dto){
        dto.add(linkTo(methodOn(VaccineCardController.class).findById(dto.getId())).withSelfRel());
    }


}
