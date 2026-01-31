package com.practica.inventario.controller;

import com.practica.inventario.entity.Inventario;
import com.practica.inventario.repository.InventarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository repository;

    // Crea los datos para probar
    @PostConstruct
    public void init() {
        if (!repository.existsById(1L)) {
            repository.save(new Inventario(1L, "Concierto", 100));
        }
    }

    @GetMapping("/validar")
    public String validar() {
        Inventario evento = repository.findById(1L).orElseThrow();

        // Verifica Stock y descuenta
        if (evento.getStock() > 0) {
            evento.setStock(evento.getStock() - 1);
            repository.save(evento);
            return "Stock disponible y descontado. Quedan: " + evento.getStock();
        } else {

            throw new RuntimeException("No hay entradas.");
        }
    }
}