package com.imunizacao.vacina.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imunizacao.vacina.model.entities.Dose;
import com.imunizacao.vacina.model.entities.VaccineCard;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

public class DoseDTO extends RepresentationModel<DoseDTO> implements Serializable {

    private Long id;
    private Date doseDate;
    private String manufacture;
    private String description;

    @JsonIgnore
    private VaccineCardDTO vaccineCardDTO;

    public DoseDTO() {
    }

    public DoseDTO(Long id, Date doseDate, String manufacture, String description, VaccineCardDTO vaccineCardDTO) {
        this.id = id;
        this.doseDate = doseDate;
        this.manufacture = manufacture;
        this.description = description;
        this.vaccineCardDTO = vaccineCardDTO;
    }

    public DoseDTO(Dose entity, VaccineCardDTO vaccineCardDTO) {
        id = entity.getId();
        doseDate = entity.getDoseDate();
        manufacture = entity.getManufacture();
        description = entity.getDescription();
        this.vaccineCardDTO = vaccineCardDTO;
    }

    public DoseDTO(Dose entity, VaccineCard vaccineCard) {
        id = entity.getId();
        doseDate = entity.getDoseDate();
        manufacture = entity.getManufacture();
        description = entity.getDescription();
        this.vaccineCardDTO = new VaccineCardDTO(vaccineCard);
    }

    public DoseDTO(Dose entity) {
        id = entity.getId();
        doseDate = entity.getDoseDate();
        manufacture = entity.getManufacture();
        description = entity.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDoseDate() {
        return doseDate;
    }

    public void setDoseDate(Date doseDate) {
        this.doseDate = doseDate;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VaccineCardDTO getVaccineCardDTO() {
        return vaccineCardDTO;
    }

    public void setVaccineCardDTO(VaccineCardDTO vaccineCardDTO) {
        this.vaccineCardDTO = vaccineCardDTO;
    }
}
