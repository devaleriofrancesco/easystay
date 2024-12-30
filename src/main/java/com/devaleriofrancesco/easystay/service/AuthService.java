package com.devaleriofrancesco.easystay.service;

import com.devaleriofrancesco.easystay.model.AuthRequest;
import com.devaleriofrancesco.easystay.model.AuthResponse;
import com.devaleriofrancesco.easystay.model.RegisterRequest;
import com.devaleriofrancesco.easystay.model.User;
import com.devaleriofrancesco.easystay.model.enums.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = (User) userService.loadUserByUsername(request.getEmail());
        var jwtToken = jwtService.generateToken(user.getUsername());
        return AuthResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNome(request.getNome());
        user.setCognome(request.getCognome());
        user.setRuolo(UserRoleEnum.ROLE_USER);
        userService.save(user);
        String jwtToken = jwtService.generateToken(user.getUsername());
        return AuthResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

}
