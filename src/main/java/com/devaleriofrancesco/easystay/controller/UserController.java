package com.devaleriofrancesco.easystay.controller;

import com.devaleriofrancesco.easystay.model.Booking;
import com.devaleriofrancesco.easystay.model.User;
import com.devaleriofrancesco.easystay.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final BookingService bookingService;

    @GetMapping("/mybookings")
    public ResponseEntity<List<Booking>> myBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(bookingService.findByCliente(user));
    }

}
