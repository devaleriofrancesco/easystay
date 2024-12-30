package com.devaleriofrancesco.easystay.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Email(message = "Email non valida")
    private String email;
    @NotBlank(message = "Password deve avere uno o più caratteri")
    private String password;

}
