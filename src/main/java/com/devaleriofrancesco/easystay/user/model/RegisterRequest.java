package com.devaleriofrancesco.easystay.user.model;

import com.devaleriofrancesco.easystay.user.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
        @NotBlank(message = "Nome deve avere uno o più caratteri")
        private String nome;
        @NotBlank(message = "Cognome deve avere uno o più caratteri")
        private String cognome;
        @NotBlank(message = "Email non valida")
        private String email;
        @NotBlank(message = "Password deve avere uno o più caratteri", groups = {ValidationGroups.RegisterRequestValidation.class})
        private String password;
}
