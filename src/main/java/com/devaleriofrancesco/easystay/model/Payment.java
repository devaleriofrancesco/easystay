package com.devaleriofrancesco.easystay.model;

import com.devaleriofrancesco.easystay.model.enums.PaymentTypeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamenti")
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "id_pagamento")
    private int id;
    private LocalDateTime dataPagamento;
    private double importo;
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento")
    private PaymentTypeEnum metodoPagamento;
    @ManyToOne
    @JoinColumn(name = "id_prenotazione")
    private Booking prenotazione;

    public int getId() {
        return id;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public PaymentTypeEnum getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(PaymentTypeEnum metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Booking getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Booking prenotazione) {
        this.prenotazione = prenotazione;
    }
}
