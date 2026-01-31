package com.practica.reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "pagos", url = "${PAGOS_URL:http://localhost:8082}")
public interface PagosClient {
    @GetMapping("/pagos/procesar")
    String procesar();
}