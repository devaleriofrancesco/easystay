package com.devaleriofrancesco.easystay.repository;


import com.devaleriofrancesco.easystay.model.RoomTypeServizi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RoomTypeServiziRepository extends JpaRepository<RoomTypeServizi, Integer> {
    @Query("SELECT rts FROM RoomTypeServizi rts JOIN rts.servizio s WHERE s.nome = :nome")
    RoomTypeServizi findByServizioNome(@Param("nome") String nome);
}
