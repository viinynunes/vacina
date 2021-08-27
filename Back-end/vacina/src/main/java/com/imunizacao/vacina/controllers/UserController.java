package com.imunizacao.vacina.controllers;

import com.imunizacao.vacina.model.User;
import com.imunizacao.vacina.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PagedResourcesAssembler<User> assembler;

    @PostMapping()
    public User create(@RequestBody User user) throws Exception {
        var entity = userService.create(user);

        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable("id") Long id) {
        var entity = userService.findById(id);

        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/findByUsername/{username}")
    public User findByUsername(@PathVariable("username") String username){
        var entity = (User) userService.loadUserByUsername(username);

        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "fullName"));

        var entityList = userService.findAll(pageable);

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }
}
