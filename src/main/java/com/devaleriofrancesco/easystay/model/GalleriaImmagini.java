package com.devaleriofrancesco.easystay.model;

import jakarta.persistence.*;

@Entity
public class GalleriaImmagini {

    @Id
    @GeneratedValue
    @Column(name = "id_immagine")
    private int id;
    @Column(name = "image_path")
    private String path_immagine;
    private int posizione;
    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    public int getId() {
        return id;
    }

    public String getPathImmagine() {
        return path_immagine;
    }

    public void setPathImmagine(String path_immagine) {
        this.path_immagine = path_immagine;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
