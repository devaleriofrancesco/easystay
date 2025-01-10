package com.devaleriofrancesco.easystay.model;

import com.devaleriofrancesco.easystay.constraint.ValidDateRange;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidDateRange(message = "Controlla che le date inserite siano corrette")
public class BookingRequest {

    @NotNull(message = "Tipo stanza è obbligatorio")
    @Min(value = 1, message = "Tipo stanza non valido")
    private int roomType;
    @NotNull(message = "Check-in è obbligatorio")
    private LocalDate dataCheckIn;
    @NotNull(message = "Check-out è obbligatorio")
    private LocalDate dataCheckOut;

}
