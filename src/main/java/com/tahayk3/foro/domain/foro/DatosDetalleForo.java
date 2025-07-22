package com.tahayk3.foro.domain.foro;

import java.time.LocalDateTime;

public record DatosDetalleForo(Long id,
                               String titulo,
                               String mensaje,
                               LocalDateTime fecha,
                               Status status,
                               DatosUsuario usuario,
                               DatosCurso curso) {
    public DatosDetalleForo(Foro foro) {
        this(
                foro.getId(),
                foro.getTitulo(),
                foro.getMensaje(),
                foro.getFecha(),
                foro.getStatus(),
                new DatosUsuario(
                        foro.getUsuario().getId(),
                        foro.getUsuario().getNombre(),
                        foro.getUsuario().getCorreo()
                ),
                new DatosCurso(
                        foro.getCurso().getId(),
                        foro.getCurso().getNombre(),
                        foro.getCurso().getCategoria()
                )
        );
    }

}
