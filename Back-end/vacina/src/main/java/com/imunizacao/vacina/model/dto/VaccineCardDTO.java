package com.imunizacao.vacina.model.dto;

import com.imunizacao.vacina.model.entities.Person;
import com.imunizacao.vacina.model.entities.VaccineCard;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

public class VaccineCardDTO extends RepresentationModel<VaccineCardDTO> implements Serializable {

    private Long id;
    private Date firstDose;
    private Date secondDose;
    private String manufacture;
    private String city;

    private Person person;

    public VaccineCardDTO(Long id, Date firstDose, Date secondDose, String manufacture, String city, Person person) {
        this.id = id;
        this.firstDose = firstDose;
        this.secondDose = secondDose;
        this.manufacture = manufacture;
        this.city = city;
        this.person = person;
    }

    public VaccineCardDTO(VaccineCard entity){
        id = entity.getId();
        firstDose = entity.getFirstDose();
        secondDose = entity.getSecondDose();
        manufacture = entity.getManufacture();
        city = entity.getCity();
        person = entity.getPerson();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFirstDose() {
        return firstDose;
    }

    public void setFirstDose(Date firstDose) {
        this.firstDose = firstDose;
    }

    public Date getSecondDose() {
        return secondDose;
    }

    public void setSecondDose(Date secondDose) {
        this.secondDose = secondDose;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
