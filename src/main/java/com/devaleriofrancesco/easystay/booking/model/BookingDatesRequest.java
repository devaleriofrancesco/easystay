package com.devaleriofrancesco.easystay.booking.model;

import com.devaleriofrancesco.easystay.booking.constraint.ValidDateRange;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidDateRange(message = "Controlla che le date inserite siano corrette")
public class BookingDatesRequest {

    @NotNull(message = "Check-in è obbligatorio")
    private LocalDate dataCheckIn;
    @NotNull(message = "Check-out è obbligatorio")
    private LocalDate dataCheckOut;

}
