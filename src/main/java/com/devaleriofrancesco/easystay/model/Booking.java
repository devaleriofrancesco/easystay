package com.devaleriofrancesco.easystay.model;

import com.devaleriofrancesco.easystay.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "prenotazioni")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "id_prenotazione")
    private int id;
    private LocalDateTime dataCheckIn;
    private LocalDateTime dataCheckOut;
    @Enumerated(EnumType.STRING)
    @Column(name = "stato_prenotazione")
    private StatusEnum statoPrenotazione;
    private double prezzoTotale;
    private LocalDateTime dataCreazione;
    private LocalDateTime dataAggiornamento;
    @ManyToOne
    @JoinColumn(name = "numero_stanza")
    private Room stanza;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private User cliente;
}

