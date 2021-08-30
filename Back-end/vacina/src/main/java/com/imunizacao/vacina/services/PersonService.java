package com.imunizacao.vacina.services;

import com.imunizacao.vacina.model.dto.PersonDTO;
import com.imunizacao.vacina.model.entities.Person;
import com.imunizacao.vacina.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDTO create (PersonDTO dto) throws Exception{

        if (personRepository.findByCpf(dto.getCpf()) != null){
            throw new Exception("CPF "+ dto.getCpf() + " is already registered");
        }

        return new PersonDTO(personRepository.save(new Person(dto)));
    }

    public PersonDTO findById(Long id){
        var entity = personRepository.findById(id).orElseThrow();

        return new PersonDTO(entity);
    }

    public Page<PersonDTO> findAll(Pageable pageable){
        var entityList = personRepository.findAll(pageable);

        return entityList.map(this::convertToDTO);
    }

    private PersonDTO convertToDTO(Person person){
        return new PersonDTO(person);
    }
}
