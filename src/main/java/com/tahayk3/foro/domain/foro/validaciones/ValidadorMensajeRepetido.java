package com.tahayk3.foro.domain.foro.validaciones;

import com.tahayk3.foro.domain.ValidacionException;
import com.tahayk3.foro.domain.foro.DatosRegistroForo;
import com.tahayk3.foro.domain.foro.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMensajeRepetido implements ValidacionesDeForos{

    @Autowired
    private ForoRepository foroRepository;

    public void validar(DatosRegistroForo datos){
        var tituloForo = foroRepository.findFirstByMensajeOrderByMensajeAsc(datos.mensaje());

        if (tituloForo != null && tituloForo.getMensaje().equals(datos.mensaje())) {
            throw new ValidacionException("El mensaje ya está registrado en un topico");
        }
    }
}
