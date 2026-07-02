package com.conaf.microservicio.despachos.controller;

import com.conaf.microservicio.despachos.dto.AsignacionRequest;
import com.conaf.microservicio.despachos.service.DespachoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/despacho")
@Tag(name = "Despachos", description = "Gestión de asignación de brigadas a incendios")
public class DespachoController {

    @Autowired
    private DespachoService despachoService;

    @Operation(summary = "Asignar brigada a un incendio", 
               description = "Recibe un DTO con el ID del incendio y asigna automáticamente una brigada disponible.")
    @ApiResponse(responseCode = "200", description = "Brigada asignada con éxito")
    @ApiResponse(responseCode = "400", description = "Error de validación o sin brigadas disponibles",
                 content = @Content(schema = @Schema(implementation = String.class),
                 examples = @ExampleObject(value = "No hay brigadas disponibles en este momento.")))
    @PostMapping("/asignar")
    public ResponseEntity<String> realizarDespacho(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos de la asignación",
                required = true,
                content = @Content(schema = @Schema(implementation = AsignacionRequest.class),
                examples = @ExampleObject(name = "Ejemplo de asignación", 
                                          value = "{\"incendioId\": 1}")))
            @RequestBody AsignacionRequest request) {
        
        return ResponseEntity.ok(despachoService.gestionarDespacho(request.incendioId()));
    }
}