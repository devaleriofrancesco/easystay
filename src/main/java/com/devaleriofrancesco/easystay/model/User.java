package com.devaleriofrancesco.easystay.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "utenti")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_ruolo")
    private Role ruolo;
    @PrePersist
    protected void onCreate() {
        dataCreazione = LocalDateTime.now();
    }
    private LocalDateTime dataCreazione;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRuolo() {
        return ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }
}
