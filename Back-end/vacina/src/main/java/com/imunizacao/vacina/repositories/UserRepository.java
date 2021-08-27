package com.imunizacao.vacina.repositories;

import com.imunizacao.vacina.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
