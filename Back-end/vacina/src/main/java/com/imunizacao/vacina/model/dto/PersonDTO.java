package com.imunizacao.vacina.model.dto;

import com.imunizacao.vacina.model.entities.Person;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private Long id;
    private String fullName;
    private String cpf;

    public PersonDTO(){

    }

    public PersonDTO(Long id, String fullName, String cpf) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
    }

    public PersonDTO(Person person){
        id = person.getId();
        fullName = person.getFullName();
        cpf = person.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
