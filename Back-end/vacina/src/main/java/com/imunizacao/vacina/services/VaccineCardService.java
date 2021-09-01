package com.imunizacao.vacina.services;

import com.imunizacao.vacina.model.dto.DoseDTO;
import com.imunizacao.vacina.model.dto.VaccineCardDTO;
import com.imunizacao.vacina.model.entities.Dose;
import com.imunizacao.vacina.model.entities.Person;
import com.imunizacao.vacina.model.entities.VaccineCard;
import com.imunizacao.vacina.repositories.DoseRepository;
import com.imunizacao.vacina.repositories.PersonRepository;
import com.imunizacao.vacina.repositories.VaccineCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VaccineCardService {

    @Autowired
    private VaccineCardRepository vaccineRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DoseRepository doseRepository;

    public VaccineCardDTO create(VaccineCardDTO dto) throws Exception {

        var entity = vaccineRepository.findByPersonID(dto.getPerson().getId());

        if (entity != null) {
            throw new Exception(entity.getPerson().getFullName() + " already have a vaccine Card");
        }

        dto.setRegistrationDate(new Date());
        dto.setPerson(getPerson(dto.getPerson().getId()));

        return new VaccineCardDTO(vaccineRepository.save(new VaccineCard(dto)));
    }

    public VaccineCardDTO insertDose(Long vaccineCardID, DoseDTO doseDTO) throws Exception {
        if (vaccineCardID == null || doseDTO == null) {
            throw new Exception("Fields cannot be null");
        }

        var vaccineEntity = vaccineRepository.findById(vaccineCardID).orElseThrow();

        doseDTO.setDoseDate(new Date());

        vaccineEntity.addDose(doseRepository.save(new Dose(doseDTO, vaccineEntity)));

        return new VaccineCardDTO(vaccineRepository.save(vaccineEntity));
    }

    public VaccineCardDTO findById(Long id) {
        var entity = vaccineRepository.findById(id).orElseThrow();
        return new VaccineCardDTO(entity);
    }

    public VaccineCardDTO findByUserCPF(String cpf) throws Exception{
        var entity = vaccineRepository.findByPersonCPF(cpf);

        if (entity == null){
            throw new Exception("User not found");
        } else
            return new VaccineCardDTO(entity);
    }

    public Page<VaccineCardDTO> findAll(Pageable pageable) {
        var entityList = vaccineRepository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    private Person getPerson(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    private VaccineCardDTO convertToDTO(VaccineCard card) {
        return new VaccineCardDTO(card);
    }


}
