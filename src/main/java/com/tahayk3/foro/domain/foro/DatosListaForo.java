package com.tahayk3.foro.domain.foro;

import com.tahayk3.foro.domain.curso.Curso;
import com.tahayk3.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListaForo(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Status status,
        DatosUsuario usuario,
        DatosCurso curso
) {
    public DatosListaForo(Foro foro) {
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
