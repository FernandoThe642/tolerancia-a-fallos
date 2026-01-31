package com.practica.notificaciones.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionesController {

    @GetMapping("/enviar")
    public String enviar() {
        return "Email de confirmación enviado";
    }
}