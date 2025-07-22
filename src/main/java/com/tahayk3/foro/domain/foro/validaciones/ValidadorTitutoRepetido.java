package com.tahayk3.foro.domain.foro.validaciones;

import com.tahayk3.foro.domain.ValidacionException;
import com.tahayk3.foro.domain.foro.DatosRegistroForo;
import com.tahayk3.foro.domain.foro.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorTitutoRepetido implements ValidacionesDeForos{

    @Autowired
    private ForoRepository foroRepository;

    public void validar(DatosRegistroForo datos){
        var tituloForo = foroRepository.findFirstByTituloOrderByTituloAsc(datos.titulo());

        if (tituloForo != null && tituloForo.getTitulo().equals(datos.titulo())) {
            throw new ValidacionException("El titulo ya está registrado");
        }
    }
}
































