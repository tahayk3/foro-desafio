package com.tahayk3.foro.domain.foro.validaciones;

import com.tahayk3.foro.domain.ValidacionException;
import com.tahayk3.foro.domain.foro.DatosActualizacionForo;
import com.tahayk3.foro.domain.foro.ForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMensajeRepetidoAactualizacion  implements ValidacionesDeActualizacion{

    @Autowired
    private ForoRepository foroRepository;

    @Override
    public void validar(DatosActualizacionForo datos) {
        if (datos.mensaje() == null) return;

        var foroConMismoMensaje = foroRepository.findFirstByMensajeOrderByMensajeAsc(datos.mensaje());

        if (foroConMismoMensaje != null && !foroConMismoMensaje.getId().equals(datos.id())) {
            throw new ValidacionException("El mensaje ya está registrado en otro tópico");
        }
    }
}
