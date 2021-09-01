package com.imunizacao.vacina.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imunizacao.vacina.model.dto.DoseDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dose")
public class Dose implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dose_date", nullable = false)
    private Date doseDate;
    @Column(name = "manufacture", nullable = false)
    private String manufacture;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "vaccine_card_id")
    private VaccineCard vaccineCard;

    public Dose() {
    }

    public Dose(Long id, Date doseDate, String manufacture, String description, VaccineCard vaccineCard) {
        this.id = id;
        this.doseDate = doseDate;
        this.manufacture = manufacture;
        this.description = description;
        this.vaccineCard = vaccineCard;
    }

    public Dose(DoseDTO dto, VaccineCard vaccineCard) {
        id = dto.getId();
        doseDate = dto.getDoseDate();
        manufacture = dto.getManufacture();
        description = dto.getDescription();
        this.vaccineCard = vaccineCard;
    }

    public Dose(DoseDTO dto) {
        id = dto.getId();
        doseDate = dto.getDoseDate();
        manufacture = dto.getManufacture();
        description = dto.getDescription();
        vaccineCard = new VaccineCard(dto.getVaccineCardDTO());
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

    public VaccineCard getVaccineCard() {
        return vaccineCard;
    }

    public void setVaccineCard(VaccineCard vaccineCard) {
        this.vaccineCard = vaccineCard;
    }
}
