package com.conaf.microservicio.despachos.controller;

import com.conaf.microservicio.despachos.service.DespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/despacho")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @PostMapping("/asignar/{incendioId}")
    public ResponseEntity<String> realizarDespacho(@PathVariable Long incendioId) {
        return ResponseEntity.ok(despachoService.gestionarDespacho(incendioId));
    }
}
