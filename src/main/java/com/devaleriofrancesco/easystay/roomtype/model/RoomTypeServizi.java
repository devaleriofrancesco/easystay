package com.devaleriofrancesco.easystay.roomtype.model;

import com.devaleriofrancesco.easystay.services.model.Servizi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "room_type_servizi")
public class RoomTypeServizi {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    @JsonIgnore
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "servizio_id")
    private Servizi servizio;

    private int qty;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Servizi getServizio() {
        return servizio;
    }

    public void setServizio(Servizi servizio) {
        this.servizio = servizio;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
