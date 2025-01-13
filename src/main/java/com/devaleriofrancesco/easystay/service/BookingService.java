package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.*;
import com.devaleriofrancesco.easystay.model.enums.StatusEnum;
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
    private final RoomTypeService roomTypeService;

    // get all bookings
    public List<Booking> findAll() {
        return bookingsRepository.findAll();
    }

    // find all bookings by check-in and check-out dates
    public List<Booking> findByCheckInAndCheckOut(LocalDate checkIn, LocalDate checkOut) {
        return bookingsRepository.findByCheckInAndCheckOut(checkIn, checkOut);
    }

    // find all bookings by check-in, check-out dates and user full name
    public List<Booking> findByCheckInAndCheckOut(LocalDate checkIn, LocalDate checkOut, String userFullName) {
        return bookingsRepository.findByCheckInAndCheckOutAndUser(checkIn, checkOut, userFullName);
    }

    // get bookings by month and year
    public List<Booking> findByMonthAndYear(Integer month, Integer year) {
        return bookingsRepository.getByMonthAndYear(month, year);
    }

    // get all bookings by specific user
    public List<Booking> findByCliente(User cliente) {
        return bookingsRepository.findByCliente(cliente);
    }

    // create new booking
    public Booking createBooking(BookingRequest bookingRequest, User user) {
        Booking booking = new Booking();
        RoomType roomType = roomTypeService.findById(bookingRequest.getRoomType());
        if (roomType == null) {
            throw new IllegalArgumentException("Tipo di stanza non trovato");
        }

        Room room = getNewRoom(bookingRequest.getDataCheckIn(), bookingRequest.getDataCheckOut(), roomType);
        booking.setStanza(room);
        booking.setDataCheckIn(bookingRequest.getDataCheckIn());
        booking.setDataCheckOut(bookingRequest.getDataCheckOut());
        booking.setPrezzoTotale(roomType.getPrezzoCompleto());
        booking.setStatoPrenotazione(StatusEnum.ATTIVA);
        booking.setCliente(user);
        return bookingsRepository.save(booking);
    }

    // Change booking check-in and check-out dates
    public Booking changeDates(Integer id, BookingDatesRequest booking, User user) {
        Booking bookingToChange = bookingsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        isBookingBelongingToUser(bookingToChange, user);
        isBookingChangeable(bookingToChange, booking);

        Room newRoom = getUpdatedRoom(bookingToChange, booking.getDataCheckIn(), booking.getDataCheckOut());
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

    public Room getNewRoom(LocalDate checkIn, LocalDate checkOut, RoomType roomType) {
        return roomService.getNewRoom(checkIn, checkOut, roomType);
    }

    // check if check-in and check-out dates collide with other bookings with the same room
    public Room getUpdatedRoom(Booking bookingToChange, LocalDate newCheckIn, LocalDate newCheckOut) {
        return roomService.getNewRoom(bookingToChange, newCheckIn, newCheckOut);
    }

    // delete booking by id
    public void deleteBooking(Integer id) {
        Booking booking = bookingsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        bookingsRepository.delete(booking);
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
