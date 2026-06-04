package com.conaf.microservicio.despachos.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistorialDespacho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long incendioId;
    private Long brigadaId;
    private LocalDateTime fechaDespacho;

    public HistorialDespacho() {}

    public HistorialDespacho(Long incendioId, Long brigadaId) {
        this.incendioId = incendioId;
        this.brigadaId = brigadaId;
        this.fechaDespacho = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIncendioId() { return incendioId; }
    public void setIncendioId(Long incendioId) { this.incendioId = incendioId; }

    public Long getBrigadaId() { return brigadaId; }
    public void setBrigadaId(Long brigadaId) { this.brigadaId = brigadaId; }

    public LocalDateTime getFechaDespacho() { return fechaDespacho; }
    public void setFechaDespacho(LocalDateTime fechaDespacho) { this.fechaDespacho = fechaDespacho; }
}
