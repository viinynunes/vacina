package com.imunizacao.vacina.repositories;

import com.imunizacao.vacina.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByCpf(String cpf);
}
