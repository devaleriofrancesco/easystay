package com.devaleriofrancesco.easystay.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class RoomTypeRequest {
    @NotBlank(message = "Nome deve avere uno o più caratteri")
    private String nome;
    @NotBlank(message = "Descrizione deve avere uno o più caratteri")
    private String descrizione;
    @Min(value = 1, message = "Numero posti letto deve essere maggiore di 0")
    private int numeroPostiLetto;
    @Min(value = 1, message = "Prezzo completo deve essere maggiore di 0")
    private double prezzoCompleto;
    @Min(value = 1, message = "Numero adulti deve essere maggiore di 0")
    private int numeroAdulti;
    @Min(value = 1, message = "Numero bambini deve essere maggiore di 0")
    private int numeroBambini;
    @Min(value = 1, message = "Metri quadri deve essere maggiore di 0")
    private int metriQuadri;
    private List<GalleriaImmagini> galleriaImmagini;
    private List<RoomTypeServiziRequest> servizi;
}
