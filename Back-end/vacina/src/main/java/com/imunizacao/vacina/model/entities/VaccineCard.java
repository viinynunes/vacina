package com.imunizacao.vacina.model.entities;

import com.imunizacao.vacina.model.dto.VaccineCardDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vaccine_card")
public class VaccineCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "city")
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "vaccineCard")
    private List<Dose> doseList = new ArrayList<>();

    public VaccineCard() {
    }

    public VaccineCard(Long id, Date registrationDate, String city, Person person) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.city = city;
        this.person = person;
    }

    public VaccineCard(VaccineCardDTO dto) {
        id = dto.getId();
        registrationDate = dto.getRegistrationDate();
        city = dto.getCity();

        person = dto.getPerson();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public List<Dose> getDoseList() {
        return doseList;
    }

    public void addDose(Dose dose) {
        doseList.add(dose);
    }

    public void removeDose(Dose dose) {
        doseList.remove(dose);
    }
}
