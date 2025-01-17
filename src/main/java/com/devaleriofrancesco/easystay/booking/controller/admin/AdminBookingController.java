package com.devaleriofrancesco.easystay.booking.controller.admin;

import com.devaleriofrancesco.easystay.booking.model.Booking;
import com.devaleriofrancesco.easystay.booking.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {

    private final BookingService bookingService;

    // get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    // get all bookings by check-in and check-out dates
    @GetMapping("/checkin/{checkin}/checkout/{checkout}")
    public ResponseEntity<List<Booking>> getBookingsByCheckInAndCheckOut(@PathVariable LocalDate checkin, @PathVariable LocalDate checkout) {
        return ResponseEntity.ok(bookingService.findByCheckInAndCheckOut(checkin, checkout));
    }

    // get all bookings by check, in checkout dates and user full name
    @GetMapping("/checkin/{checkin}/checkout/{checkout}/user/{userFullName}")
    public ResponseEntity<List<Booking>> getBookingsByCheckInAndCheckOutAndUser(@PathVariable LocalDate checkin, @PathVariable LocalDate checkout, @PathVariable String userFullName) {
        return ResponseEntity.ok(bookingService.findByCheckInAndCheckOut(checkin, checkout, userFullName));
    }

    // get bookings by month and year
    @GetMapping("/month/{month}/year/{year}")
    public ResponseEntity<List<Booking>> getBookingsByMonthAndYear(@PathVariable Integer month, @PathVariable Integer year) {
        return ResponseEntity.ok(bookingService.findByMonthAndYear(month, year));
    }

    // delete booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }

}
