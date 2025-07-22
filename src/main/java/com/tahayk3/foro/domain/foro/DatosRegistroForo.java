package com.tahayk3.foro.domain.foro;

import com.tahayk3.foro.domain.curso.Categoria;
import com.tahayk3.foro.domain.curso.Curso;
import com.tahayk3.foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroForo(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String fecha,
        @NotNull
        Status status,
        @NotNull
        Long usuario_id,
        @NotNull
        Long curso_id
) {
}
