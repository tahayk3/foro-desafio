package com.tahayk3.foro.domain.foro.validaciones;

import com.tahayk3.foro.domain.ValidacionException;
import com.tahayk3.foro.domain.foro.DatosActualizacionForo;
import com.tahayk3.foro.domain.foro.DatosRegistroForo;
import com.tahayk3.foro.domain.foro.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorIdForoCorrecto {

    @Autowired
    private ForoRepository foroRepository;


    public void validar(Long id) {
        if (foroRepository.findById(id).isEmpty()) {
            throw new ValidacionException("No existe un foro con el ID especificado");
        }
    }
}