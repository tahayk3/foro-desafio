package com.tahayk3.foro.domain.foro;

import com.tahayk3.foro.domain.curso.Curso;
import com.tahayk3.foro.domain.usuario.Usuario;


public record DatosRespuestaForo(Long id,
                                 String titulo,
                                 String mensaje,
                                 Status status,
                                 DatosCurso curso,
                                 DatosUsuario usuario) {

}


