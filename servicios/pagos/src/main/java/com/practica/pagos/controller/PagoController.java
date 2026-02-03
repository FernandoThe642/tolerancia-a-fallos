package com.practica.pagos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @GetMapping("/procesar")
    public String procesar() throws InterruptedException {

        // Simulación de pasarela Lenta
        //Thread.sleep(20000);

        System.out.println("Pago recibido y procesado");
        return "Pago Exitoso";
    }
}