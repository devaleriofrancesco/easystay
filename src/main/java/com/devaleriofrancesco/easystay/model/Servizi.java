package com.devaleriofrancesco.easystay.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "servizi")
public class Servizi {

    @Id
    @GeneratedValue
    @Column(name = "id_servizio")
    private int id;
    private String nome;
    private int qty;
    private double prezzoAddizionale;
    @ManyToMany(mappedBy = "servizi")
    private List<RoomType> roomTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrezzoAddizionale() {
        return prezzoAddizionale;
    }

    public void setPrezzoAddizionale(double prezzoAddizionale) {
        this.prezzoAddizionale = prezzoAddizionale;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
