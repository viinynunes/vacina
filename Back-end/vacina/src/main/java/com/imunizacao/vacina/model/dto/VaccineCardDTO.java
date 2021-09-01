package com.imunizacao.vacina.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.imunizacao.vacina.model.entities.Dose;
import com.imunizacao.vacina.model.entities.Person;
import com.imunizacao.vacina.model.entities.VaccineCard;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "registrationDate", "city", "person", "doseDTOList", "links"})
public class VaccineCardDTO extends RepresentationModel<VaccineCardDTO> implements Serializable {

    private Long id;
    private Date registrationDate;
    private String city;

    private Person person;

    private List<DoseDTO> doseDTOList;

    public VaccineCardDTO(Long id, Date registrationDate, String city, Person person) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.city = city;
        this.person = person;
    }

    public VaccineCardDTO(VaccineCard entity){
        id = entity.getId();
        registrationDate = entity.getRegistrationDate();
        city = entity.getCity();
        person = entity.getPerson();

        doseDTOList = convertListToDTO(entity.getDoseList());
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

    public List<DoseDTO> getDoseDTOList() {
        return doseDTOList;
    }

    public void setDoseDTOList(List<DoseDTO> doseDTOList) {
        this.doseDTOList = doseDTOList;
    }

    private List<DoseDTO> convertListToDTO(List<Dose> doseList){
        List<DoseDTO> dtoList = new ArrayList<>();
        for (Dose x : doseList){
            dtoList.add(new DoseDTO(x));
        }

        return dtoList;
    }
}
