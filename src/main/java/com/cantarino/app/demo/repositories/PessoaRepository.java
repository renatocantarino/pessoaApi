package com.cantarino.app.demo.repositories;

import com.cantarino.app.demo.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Optional<Pessoa> findByCpf(String cpf);
}