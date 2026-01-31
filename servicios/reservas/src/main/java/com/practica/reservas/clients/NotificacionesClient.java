package com.practica.reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "notificaciones", url = "${NOTIFICACIONES_URL:http://localhost:8083}")
public interface NotificacionesClient {
    @GetMapping("/notificaciones/enviar")
    String enviar();
}