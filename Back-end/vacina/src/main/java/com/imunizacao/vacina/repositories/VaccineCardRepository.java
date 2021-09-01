package com.imunizacao.vacina.repositories;

import com.imunizacao.vacina.model.dto.VaccineCardDTO;
import com.imunizacao.vacina.model.entities.VaccineCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VaccineCardRepository extends JpaRepository<VaccineCard, Long> {

    @Query("SELECT v FROM VaccineCard v INNER JOIN v.person p WHERE p.id = :id")
    VaccineCard findByPersonID(Long id);

    @Query("SELECT v FROM VaccineCard v INNER JOIN v.person p WHERE p.cpf = :cpf")
    VaccineCard findByPersonCPF(String cpf);
}
