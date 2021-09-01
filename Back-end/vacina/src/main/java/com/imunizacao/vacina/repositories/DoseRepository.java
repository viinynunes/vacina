package com.imunizacao.vacina.repositories;

import com.imunizacao.vacina.model.entities.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose, Long> {

}
