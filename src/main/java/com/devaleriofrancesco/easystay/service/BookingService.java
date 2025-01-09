package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.Booking;
import com.devaleriofrancesco.easystay.model.BookingDatesRequest;
import com.devaleriofrancesco.easystay.model.Room;
import com.devaleriofrancesco.easystay.model.User;
import com.devaleriofrancesco.easystay.repository.BookingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingsRepository bookingsRepository;
    private final RoomService roomService;

    // get all bookings by specific user
    public List<Booking> findByCliente(User cliente) {
        return bookingsRepository.findByCliente(cliente);
    }

    // Change booking check-in and check-out dates
    public Booking changeDates(Integer id, BookingDatesRequest booking, User user) {
        Booking bookingToChange = bookingsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        isBookingBelongingToUser(bookingToChange, user);
        isBookingChangeable(bookingToChange, booking);

        Room newRoom = getNewRoom(bookingToChange, booking.getDataCheckIn(), booking.getDataCheckOut());
        if (!newRoom.equals(bookingToChange.getStanza())) {
            bookingToChange.setStanza(newRoom);
        }
        bookingToChange.setDataCheckIn(booking.getDataCheckIn());
        bookingToChange.setDataCheckOut(booking.getDataCheckOut());
        return bookingsRepository.save(bookingToChange);
    }

    private void isBookingChangeable(Booking bookingToChange, BookingDatesRequest newBookingDates) {

        // check if the booking is in the past
        if (bookingToChange.getDataCheckIn().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non puoi modificare una prenotazione passata");
        }

        // check if the new booking dates are 24 hours before the check-in date
        if (newBookingDates.getDataCheckIn().isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("Non puoi modificare una prenotazione con meno di 24 ore di anticipo");
        }

    }

    // check if check-in and check-out dates collide with other bookings with the same room
    public Room getNewRoom(Booking bookingToChange, LocalDate newCheckIn, LocalDate newCheckOut) {
        return roomService.getNewRoom(bookingToChange, newCheckIn, newCheckOut);
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
