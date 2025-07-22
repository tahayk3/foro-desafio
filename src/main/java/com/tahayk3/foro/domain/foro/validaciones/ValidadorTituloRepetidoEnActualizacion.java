package com.tahayk3.foro.domain.foro.validaciones;

import com.tahayk3.foro.domain.ValidacionException;
import com.tahayk3.foro.domain.foro.DatosActualizacionForo;
import com.tahayk3.foro.domain.foro.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTituloRepetidoEnActualizacion implements ValidacionesDeActualizacion {

    @Autowired
    private ForoRepository foroRepository;

    @Override
    public void validar(DatosActualizacionForo datos) {
        var tituloForo = foroRepository.findFirstByTituloOrderByTituloAsc(datos.titulo());

        if (tituloForo != null && !tituloForo.getId().equals(datos.id())) {
            throw new ValidacionException("El título ya está registrado por otro foro.");
        }
    }
}