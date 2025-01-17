package com.devaleriofrancesco.easystay.jwt.config;

import com.devaleriofrancesco.easystay.jwt.filter.JwtAuthFilter;
import com.devaleriofrancesco.easystay.jwt.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class JwtConfig {
    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        return new JwtAuthFilter(jwtService, userDetailsService);
    }
}
