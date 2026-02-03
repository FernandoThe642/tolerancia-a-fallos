package com.practica.reservas.controller;

import com.practica.reservas.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

// Endpoint para probar el flujo completo con resiliencia
// http://localhost:8080/reservas/comprar
    @GetMapping("/reservas/comprar")

    public CompletableFuture<String> probarCompra() {
        return reservaService.realizarCompraCompleta();
    }
}