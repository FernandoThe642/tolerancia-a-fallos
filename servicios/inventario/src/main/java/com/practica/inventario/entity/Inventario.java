package com.practica.inventario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    private Long id;
    private String evento;
    private int stock;


    public Inventario() {}

    public Inventario(Long id, String evento, int stock) {
        this.id = id;
        this.evento = evento;
        this.stock = stock;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}