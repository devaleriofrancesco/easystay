package com.devaleriofrancesco.easystay.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipo_camera")
public class RoomType {

    @Id
    @GeneratedValue
    @Column(name = "id_tipo_stanza")
    private int id;
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
    @ManyToMany
    @JoinTable(
            name = "tipo_stanza_servizi",
            joinColumns = @JoinColumn(name = "id_tipo_stanza"),
            inverseJoinColumns = @JoinColumn(name = "id_servizio")
    )
    private List<Servizi> servizi;

    public int getId() {
        return id;
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

    public List<Servizi> getServizi() {
        return servizi;
    }

    public void setServizi(List<Servizi> servizi) {
        this.servizi = servizi;
    }
}
