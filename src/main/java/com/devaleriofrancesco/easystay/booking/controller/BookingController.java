package com.devaleriofrancesco.easystay.booking.controller;

import com.devaleriofrancesco.easystay.booking.model.Booking;
import com.devaleriofrancesco.easystay.booking.model.BookingDatesRequest;
import com.devaleriofrancesco.easystay.booking.model.BookingRequest;
import com.devaleriofrancesco.easystay.user.model.User;
import com.devaleriofrancesco.easystay.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/bookings")
public class BookingController {

    private final BookingService bookingService;

    // get all bookings by specific user
    @GetMapping("")
    public ResponseEntity<List<Booking>> myBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(bookingService.findByCliente(user));
    }

    // create new booking
    @PostMapping("")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequest booking) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(bookingService.createBooking(booking, user));
    }

    // Change booking check-in and check-out dates
    @PutMapping("/change-dates/{id}")
    public ResponseEntity<Booking> changeDates(@PathVariable Integer id, @Valid @RequestBody BookingDatesRequest booking) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(bookingService.changeDates(id, booking, user));
    }

    // delete booking by id checking if the user is the owner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        bookingService.deleteBooking(id, user);
        return ResponseEntity.noContent().build();
    }

}
