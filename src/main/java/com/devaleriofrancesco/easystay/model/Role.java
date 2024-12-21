package com.devaleriofrancesco.easystay.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruoli")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "id_ruolo")
    private Integer id;
    private String nome;

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

}
