package com.imunizacao.vacina.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imunizacao.vacina.model.dto.VaccineCardDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vaccine_card")
public class VaccineCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_dose")
    private Date firstDose;
    @Column(name = "second_dose")
    private Date secondDose;
    @Column(name = "manufacture")
    private String manufacture;
    @Column(name = "city")
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Person person;

    public VaccineCard(Long id, Date firstDose, Date secondDose, String manufacture, String city, Person person) {
        this.id = id;
        this.firstDose = firstDose;
        this.secondDose = secondDose;
        this.manufacture = manufacture;
        this.city = city;
        this.person = person;
    }

    public VaccineCard(VaccineCardDTO dto){
        id = dto.getId();
        firstDose = dto.getFirstDose();
        secondDose = dto.getSecondDose();
        manufacture = dto.getManufacture();
        city = dto.getCity();

        person = dto.getPerson();
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
