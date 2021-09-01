package com.imunizacao.vacina.services;

import com.imunizacao.vacina.model.dto.DoseDTO;
import com.imunizacao.vacina.model.entities.Dose;
import com.imunizacao.vacina.repositories.DoseRepository;
import com.imunizacao.vacina.repositories.VaccineCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoseService {

    @Autowired
    private DoseRepository doseRepository;

    @Autowired
    private VaccineCardRepository vaccineCardRepository;

    public DoseService() {
    }

    public DoseDTO findById(Long id) throws Exception{
        return new DoseDTO(doseRepository.findById(id).orElseThrow());
    }

    public Page<DoseDTO> findAll(Pageable pageable){
        var entityList = doseRepository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    private DoseDTO convertToDTO(Dose dose){
        return new DoseDTO(dose);
    }
}
