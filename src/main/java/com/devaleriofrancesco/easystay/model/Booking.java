package com.devaleriofrancesco.easystay.model;

import com.devaleriofrancesco.easystay.model.enums.StatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public LocalDateTime getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(LocalDateTime dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public LocalDateTime getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(LocalDateTime dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public StatusEnum getStatoPrenotazione() {
        return statoPrenotazione;
    }

    public void setStatoPrenotazione(StatusEnum statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public LocalDateTime getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public Room getStanza() {
        return stanza;
    }

    public void setStanza(Room stanza) {
        this.stanza = stanza;
    }

    public User getCliente() {
        return cliente;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }
}

