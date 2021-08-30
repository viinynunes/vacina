package com.imunizacao.vacina.services;

import com.imunizacao.vacina.model.dto.VaccineCardDTO;
import com.imunizacao.vacina.model.entities.Person;
import com.imunizacao.vacina.model.entities.VaccineCard;
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

    public VaccineCardDTO create(VaccineCardDTO dto) throws Exception {

        var entity = vaccineRepository.findByPerson(dto.getPerson().getCpf());

        if (entity != null) {
            throw new Exception("User " + dto.getPerson().getFullName() + " is already registered");
        }

        dto.setRegistrationDate(new Date());
        dto.setPerson(getPerson(dto.getPerson().getId()));

        return new VaccineCardDTO(vaccineRepository.save(new VaccineCard(dto)));
    }

    public VaccineCardDTO findById(Long id){
        var entity = vaccineRepository.findById(id).orElseThrow();
        return new VaccineCardDTO(entity);
    }

    public Page<VaccineCardDTO> findAll(Pageable pageable){
        var entityList = vaccineRepository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    private Person getPerson(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    private VaccineCardDTO convertToDTO(VaccineCard card){
        return new VaccineCardDTO(card);
    }


}
