package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.Booking;
import com.devaleriofrancesco.easystay.model.User;
import com.devaleriofrancesco.easystay.repository.BookingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingsRepository bookingsRepository;

    // get all bookings by specific user
    public List<Booking> findByCliente(User cliente) {
        return bookingsRepository.findByCliente(cliente);
    }

}
