package com.devaleriofrancesco.easystay.repository;

import com.devaleriofrancesco.easystay.model.Servizi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiziRepository extends JpaRepository<Servizi, Integer> {

    Servizi findFirstByNome(String nome);

}
