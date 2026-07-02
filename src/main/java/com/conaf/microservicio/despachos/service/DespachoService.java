package com.conaf.microservicio.despachos.service;

import com.conaf.microservicio.despachos.exception.DespachoException;
import com.conaf.microservicio.despachos.model.HistorialDespacho;
import com.conaf.microservicio.despachos.repository.HistorialDespachoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class DespachoService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private HistorialDespachoRepository historialRepository;

    public String gestionarDespacho(Long incendioId) {
        try {
            Map<String, Object> incendio = webClientBuilder.build().get()
                .uri("https://incendios.onrender.com/api/incendios/" + incendioId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

            if (incendio == null) {
                throw new DespachoException("No se encontró el incendio con ID: " + incendioId);
            }

            List<Map<String, Object>> brigadasDisponibles = webClientBuilder.build().get()
                .uri("https://brigadas-lj77.onrender.com/api/brigadas/filtro/estado/Disponible")
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Map<String, Object>>() {})
                .collectList()
                .block();

            if (brigadasDisponibles == null || brigadasDisponibles.isEmpty()) {
                throw new DespachoException("No hay brigadas disponibles en este momento.");
            }

            Map<String, Object> brigada = brigadasDisponibles.get(0);
            Integer brigadaId = (Integer) brigada.get("id");

            webClientBuilder.build().patch()
                .uri("https://brigadas-lj77.onrender.com/api/brigadas/" + brigadaId + "/estado?nuevoEstado=En Combate")
                .retrieve()
                .toBodilessEntity()
                .block();

            historialRepository.save(new HistorialDespacho(incendioId, brigadaId.longValue()));

            return "Brigada " + brigadaId + " asignada con éxito al incendio " + incendioId;

        } catch (Exception e) {
            if (e instanceof DespachoException) throw e;
            
            throw new DespachoException("Error crítico al conectar con microservicios: " + e.getMessage());
        }
    }
}