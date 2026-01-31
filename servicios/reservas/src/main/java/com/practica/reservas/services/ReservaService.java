package com.practica.reservas.services;

import com.practica.reservas.clients.InventarioClient;
import com.practica.reservas.clients.NotificacionesClient;
import com.practica.reservas.clients.PagosClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Service
public class ReservaService {

    @Autowired private InventarioClient inventarioClient;
    @Autowired private PagosClient pagosClient;
    @Autowired private NotificacionesClient notificacionesClient;

    // Configuración de Resiliencia: Circuit Breaker + TimeLimiter de 3 segundos
    @CircuitBreaker(name = "inventarioCB", fallbackMethod = "falloInventario")
    @TimeLimiter(name = "pagosTimeout")
    public CompletableFuture<String> realizarCompraCompleta() {
        return CompletableFuture.supplyAsync(() -> {

            // Llama al Servicio de Inventario si falla se salta al Método Fallback
            inventarioClient.validar();

            pagosClient.procesar();

           // Notificaciones: Fallo no crítico
            try {
                notificacionesClient.enviar();
            } catch (Exception e) {
                System.out.println("Email no enviado, pero reserva confirmada");
            }

            return "Compra realizada con exito";
        });
    }

    // Método Fallback
    public CompletableFuture<String> falloInventario(Throwable e) {

        // Si el fallo fué por tiempo
        if (e instanceof TimeoutException) {
            return CompletableFuture.completedFuture("El Servicio  de Pagos tardó demasiado y se cortó su llamada.");
        }

        // Si el fallo fuel por el inventario
        return CompletableFuture.completedFuture(" El Servicio de Inventario no disponible: Circuit Breaker abierto.");
    }
}