package com.tahayk3.foro.domain.foro;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizacionForo(
        @NotNull Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Status status,
        DatosUsuario usuario,
        DatosCurso curso
) {
}
