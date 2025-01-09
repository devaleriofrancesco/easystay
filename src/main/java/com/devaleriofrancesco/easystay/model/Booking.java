package com.devaleriofrancesco.easystay.model;

import com.devaleriofrancesco.easystay.model.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
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
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    @Enumerated(EnumType.STRING)
    @Column(name = "stato_prenotazione")
    private StatusEnum statoPrenotazione;
    private double prezzoTotale;
    @CreationTimestamp
    private LocalDateTime dataCreazione;
    @UpdateTimestamp
    private LocalDateTime dataAggiornamento;
    @ManyToOne
    @JoinColumn(name = "numero_stanza")
    private Room stanza;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private User cliente;
}

