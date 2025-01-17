package com.devaleriofrancesco.easystay.room.model;

import com.devaleriofrancesco.easystay.roomtype.model.RoomType;
import jakarta.persistence.*;

@Entity
@Table(name = "stanze")
public class Room {

    @Id
    @Column(name = "numero_stanza")
    private Long numeroStanza;
    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType tipoStanza;
    private boolean prenotabile;

    public Long getNumeroStanza() {
        return numeroStanza;
    }

    public void setNumeroStanza(Long numeroStanza) {
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
