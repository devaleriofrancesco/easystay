package com.devaleriofrancesco.easystay.repository;

import com.devaleriofrancesco.easystay.model.Booking;
import com.devaleriofrancesco.easystay.model.Room;
import com.devaleriofrancesco.easystay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCliente(User cliente);
    List<Booking> findByStanza(Room stanza);
}
