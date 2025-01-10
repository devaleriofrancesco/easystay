package com.devaleriofrancesco.easystay.repository;

import com.devaleriofrancesco.easystay.model.Room;
import com.devaleriofrancesco.easystay.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r " +
            "WHERE NOT EXISTS (" +
            "SELECT b FROM Booking b " +
            "WHERE :checkIn < b.dataCheckOut " +
            "AND :checkOut > b.dataCheckIn " +
            "AND b.id != :currentBookingId " +
            "AND b.stanza = r" +
            ") " +
            "AND r.tipoStanza = :roomType " +
            "AND r.prenotabile = true "
    )
    Page<Room> findFirstAvailableRoom(
            Pageable pageable,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("roomType") RoomType roomType,
            @Param("currentBookingId") Integer currentBookingId
    );

    @Query("SELECT r FROM Room r " +
            "WHERE NOT EXISTS (" +
            "SELECT b FROM Booking b " +
            "WHERE :checkIn < b.dataCheckOut " +
            "AND :checkOut > b.dataCheckIn " +
            ") " +
            "AND r.tipoStanza = :roomType " +
            "AND r.prenotabile = true "
    )
    Page<Room> findFirstAvailableRoom(
            Pageable pageable,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("roomType") RoomType roomType
    );

}
