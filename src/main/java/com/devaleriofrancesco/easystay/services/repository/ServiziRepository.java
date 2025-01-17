package com.devaleriofrancesco.easystay.services.repository;

import com.devaleriofrancesco.easystay.roomtype.model.RoomType;
import com.devaleriofrancesco.easystay.services.model.Servizi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiziRepository extends JpaRepository<Servizi, Integer> {

    Servizi findFirstByNome(String nome);
    @Query("SELECT s FROM Servizi s WHERE NOT EXISTS (SELECT rts FROM RoomTypeServizi rts WHERE rts.servizio = s AND rts.roomType = :roomType)")
    List<Servizi> findAllServicesNotAssociatedWithRoomType(
            @Param("roomType") RoomType roomType
    );

}
