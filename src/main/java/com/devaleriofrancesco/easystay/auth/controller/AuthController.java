package com.devaleriofrancesco.easystay.auth.controller;

import com.devaleriofrancesco.easystay.auth.model.AuthRequest;
import com.devaleriofrancesco.easystay.auth.model.AuthResponse;
import com.devaleriofrancesco.easystay.user.model.RegisterRequest;
import com.devaleriofrancesco.easystay.auth.service.AuthService;
import com.devaleriofrancesco.easystay.user.validation.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // response for login with token
    @PostMapping("/token")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    // response for register with token
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Validated({ValidationGroups.RegisterRequestValidation.class, Default.class}) @RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authService.register(registerRequest));
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("E-Mail già presente nel sistema");
        }
    }

}
