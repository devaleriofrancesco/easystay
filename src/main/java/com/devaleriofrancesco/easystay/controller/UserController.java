package com.devaleriofrancesco.easystay.controller;

import com.devaleriofrancesco.easystay.model.AuthResponse;
import com.devaleriofrancesco.easystay.model.RegisterRequest;
import com.devaleriofrancesco.easystay.model.User;
import com.devaleriofrancesco.easystay.service.AuthService;
import com.devaleriofrancesco.easystay.validation.ValidationGroups;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final AuthService authService;

    // update user action
    @PutMapping
    public ResponseEntity<AuthResponse> updateUser(@Validated({ValidationGroups.UpdateUserValidation.class, Default.class}) @RequestBody RegisterRequest registerRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(authService.updateUser(user, registerRequest));
    }

}
