package com.imunizacao.vacina.repositories;

import com.imunizacao.vacina.model.entities.Person;
import com.imunizacao.vacina.model.entities.VaccineCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VaccineCardRepository extends JpaRepository<VaccineCard, Long> {

    @Query("SELECT v FROM VaccineCard v INNER JOIN v.person p WHERE p.cpf = :cpf")
    VaccineCard findByPerson (String cpf);
}
