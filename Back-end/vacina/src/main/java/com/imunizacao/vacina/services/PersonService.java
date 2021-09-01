package com.imunizacao.vacina.services;

import com.imunizacao.vacina.exception.ResourceAlreadyExists;
import com.imunizacao.vacina.exception.ResourceNotFound;
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

    public PersonDTO create (PersonDTO dto){

        if (personRepository.findByCpf(dto.getCpf()) != null){
            throw new ResourceAlreadyExists("CPF "+ dto.getCpf() + " is already registered");
        }

        return new PersonDTO(personRepository.save(new Person(dto)));
    }

    public PersonDTO findById(Long id){
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Person not found"));

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
