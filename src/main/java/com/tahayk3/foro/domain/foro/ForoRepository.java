package com.tahayk3.foro.domain.foro;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForoRepository extends JpaRepository<Foro, Long> {
    Page<Foro> findAll(Pageable paginacion);

    Foro findFirstByTituloOrderByTituloAsc(String titulo);

    Foro findFirstByMensajeOrderByMensajeAsc(String mensaje);
}
