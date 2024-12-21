package com.devaleriofrancesco.easystay.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stanze")
public class Room {

    @Id
    @Column(name = "numero_stanza")
    private long numeroStanza;
    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType tipoStanza;
    private boolean prenotabile;

    public long getNumeroStanza() {
        return numeroStanza;
    }

    public void setNumeroStanza(long numeroStanza) {
        this.numeroStanza = numeroStanza;
    }

    public RoomType getTipoStanza() {
        return tipoStanza;
    }

    public void setTipoStanza(RoomType tipoStanza) {
        this.tipoStanza = tipoStanza;
    }

    public boolean isPrenotabile() {
        return prenotabile;
    }

    public void setPrenotabile(boolean prenotabile) {
        this.prenotabile = prenotabile;
    }
}
