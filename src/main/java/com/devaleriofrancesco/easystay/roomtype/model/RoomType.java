package com.devaleriofrancesco.easystay.roomtype.model;

import com.devaleriofrancesco.easystay.gallery.model.GalleriaImmagini;
import com.devaleriofrancesco.easystay.room.model.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipo_camera")
public class RoomType {

    @Id
    @GeneratedValue
    @Column(name = "id_tipo_stanza")
    private Integer id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String descrizione;
    private double prezzo;
    private int metriQuadri;
    private int numeroAdulti;
    private int numeroBambini;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GalleriaImmagini> galleriaImmagini;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("servizi")
    private List<RoomTypeServizi> roomTypeServizi;

    @OneToMany(mappedBy = "tipoStanza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> stanze;

    public int getId() {
        return id;
    }

    public double getPrezzoCompleto() {
        List<RoomTypeServizi> servizi = roomTypeServizi;
        double prezzoServizi = 0;
        for (RoomTypeServizi servizio : servizi) {
            prezzoServizi += (servizio.getServizio().getPrezzoAddizionale() * servizio.getQty());
        }
        return prezzoServizi + prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getMetriQuadri() {
        return metriQuadri;
    }

    public void setMetriQuadri(int metriQuadri) {
        this.metriQuadri = metriQuadri;
    }

    public int getNumeroAdulti() {
        return numeroAdulti;
    }

    public int getNumeroPostiLetto() {
        return numeroAdulti + numeroBambini;
    }

    public void setNumeroAdulti(int numeroAdulti) {
        this.numeroAdulti = numeroAdulti;
    }

    public int getNumeroBambini() {
        return numeroBambini;
    }

    public void setNumeroBambini(int numeroBambini) {
        this.numeroBambini = numeroBambini;
    }

    public List<GalleriaImmagini> getGalleriaImmagini() {
        return galleriaImmagini;
    }

    public void setGalleriaImmagini(List<GalleriaImmagini> galleriaImmagini) {
        this.galleriaImmagini = galleriaImmagini;
    }

    public List<RoomTypeServizi> getRoomTypeServizi() {
        return roomTypeServizi;
    }

    public void setRoomTypeServizi(List<RoomTypeServizi> roomTypeServizi) {
        this.roomTypeServizi = roomTypeServizi;
    }
}
