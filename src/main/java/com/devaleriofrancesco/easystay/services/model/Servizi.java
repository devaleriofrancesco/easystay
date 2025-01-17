package com.devaleriofrancesco.easystay.services.model;

import com.devaleriofrancesco.easystay.roomtype.model.RoomTypeServizi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "servizi", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Servizi {

    @Id
    @GeneratedValue
    @Column(name = "id_servizio")
    private Integer id = 0;
    @Column(nullable = false, unique = true)
    private String nome;
    private double prezzoAddizionale;
    @OneToMany(mappedBy = "servizio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RoomTypeServizi> roomTypeServizi;

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

    public double getPrezzoAddizionale() {
        return prezzoAddizionale;
    }

    public void setPrezzoAddizionale(double prezzoAddizionale) {
        this.prezzoAddizionale = prezzoAddizionale;
    }

    public List<RoomTypeServizi> getRoomTypeServizi() {
        return roomTypeServizi;
    }

    public void setRoomTypeServizi(List<RoomTypeServizi> roomTypeServizi) {
        this.roomTypeServizi = roomTypeServizi;
    }
}
