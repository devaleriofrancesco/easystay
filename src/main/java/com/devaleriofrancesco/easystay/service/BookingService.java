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

    // Change booking check-in and check-out dates
    public Booking changeDates(Integer id, Booking booking, User user) {
        Booking bookingToChange = bookingsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        isBookingBelongingToUser(bookingToChange, user);
        if (checkDatesCollide(booking)) {
            throw new IllegalArgumentException("Le date di check-in e check-out collidono con altre prenotazioni");
        }
        bookingToChange.setDataCheckIn(booking.getDataCheckIn());
        bookingToChange.setDataCheckOut(booking.getDataCheckOut());
        return bookingsRepository.save(bookingToChange);
    }

    // check if check-in and check-out dates collide with other bookings with the same room
    public boolean checkDatesCollide(Booking booking) {
        List<Booking> bookings = bookingsRepository.findByStanza(booking.getStanza());
        for (Booking b : bookings) {
            if (booking.getDataCheckIn().isBefore(b.getDataCheckOut()) && booking.getDataCheckOut().isAfter(b.getDataCheckIn())) {
                return true;
            }
        }
        return false;
    }

    // delete booking by id
    public void deleteBooking(Integer id, User user) {
        Booking booking = bookingsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        isBookingBelongingToUser(booking, user);
        bookingsRepository.delete(booking);
    }

    // check if the booking belongs to the user
    private void isBookingBelongingToUser(Booking booking, User user) {
        if (!booking.getCliente().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Non sei il proprietario di questa prenotazione");
        }
    }

}
