package com.imunizacao.vacina.model.entities;

import com.imunizacao.vacina.model.dto.PersonDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    public Person(){
    }

    public Person(Long id, String fullName, String cpf) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
    }

    public Person(PersonDTO dto){
        id = dto.getId();
        fullName = dto.getFullName();
        cpf = dto.getCpf();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(fullName, person.fullName) && Objects.equals(cpf, person.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
