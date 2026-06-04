package com.conaf.microservicio.despachos.repository;

import com.conaf.microservicio.despachos.model.HistorialDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialDespachoRepository extends JpaRepository<HistorialDespacho, Long> {
}
