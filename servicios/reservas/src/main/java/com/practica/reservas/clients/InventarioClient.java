package com.practica.reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "inventario", url = "${INVENTARIO_URL:http://localhost:8081}")
public interface InventarioClient {

    @GetMapping("/inventario/validar")
    String validar();
}