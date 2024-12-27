package com.devaleriofrancesco.easystay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
        private String nome;
        private String cognome;
        private String email;
        private String password;
}
