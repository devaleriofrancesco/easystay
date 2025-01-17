package com.devaleriofrancesco.easystay.booking.repository;

import com.devaleriofrancesco.easystay.booking.model.Booking;
import com.devaleriofrancesco.easystay.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCliente(User cliente);

    @Query("SELECT b FROM Booking b WHERE MONTH(b.dataCheckIn) = :month AND YEAR(b.dataCheckIn) = :year")
    List<Booking> getByMonthAndYear(
            @Param("month") Integer month,
            @Param("year") Integer year
    );

    @Query("SELECT b FROM Booking b WHERE b.dataCheckIn >= :checkIn AND b.dataCheckOut <= :checkOut")
    List<Booking> findByCheckInAndCheckOut(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut
    );

    @Query("SELECT b FROM Booking b WHERE b.dataCheckIn >= :checkIn AND b.dataCheckOut <= :checkOut AND LOWER(CONCAT(b.cliente.nome, ' ', b.cliente.cognome)) LIKE CONCAT('%', :userFullName, '%')")
    List<Booking> findByCheckInAndCheckOutAndUser(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("userFullName") String userFullName
    );

}
