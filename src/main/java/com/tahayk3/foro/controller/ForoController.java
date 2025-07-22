package com.tahayk3.foro.controller;

import com.tahayk3.foro.domain.ValidacionException;
import com.tahayk3.foro.domain.curso.Curso;
import com.tahayk3.foro.domain.curso.CursoRepository;
import com.tahayk3.foro.domain.foro.*;
import com.tahayk3.foro.domain.foro.validaciones.ValidacionesDeActualizacion;
import com.tahayk3.foro.domain.foro.validaciones.ValidacionesDeForos;
import com.tahayk3.foro.domain.foro.validaciones.ValidadorIdForoCorrecto;
import com.tahayk3.foro.domain.usuario.Usuario;
import com.tahayk3.foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;




import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class ForoController {

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @Autowired
    private List<ValidacionesDeForos> validadores;

    @Autowired
    private List<ValidacionesDeActualizacion> validadoresActualizacion;


    @Autowired
    private ValidadorIdForoCorrecto validadorIdForoCorrecto;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosRespuestaForo> registrarForo(@RequestBody @Valid DatosRegistroForo datosRegistroForo,
                                                            UriComponentsBuilder uriComponentsBuilder){

        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroForo.usuario_id());
        Curso curso = cursoRepository.getReferenceById(datosRegistroForo.curso_id());

        Foro foro = new Foro(datosRegistroForo, usuario, curso);
        //validaciones
        validadores.forEach(v -> v.validar(datosRegistroForo));
        foroRepository.save(foro);

        DatosRespuestaForo datosRespuestaForo = new DatosRespuestaForo(
                foro.getId(),
                foro.getTitulo(),
                foro.getMensaje(),
                foro.getStatus(),
                new DatosCurso(curso.getId(), curso.getNombre(), curso.getCategoria()),
                new DatosUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreo()));

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(foro.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaForo);

    }


    @GetMapping
    public ResponseEntity<Page<DatosListaForo>> listar(@PageableDefault(size=10, sort={"titulo"}) Pageable paginacion) {
        var page = foroRepository.findAll(paginacion).map(DatosListaForo::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        validadorIdForoCorrecto.validar(id);
        var foroOptional = foroRepository.findById(id);
        var foro = foroOptional.get();
        return ResponseEntity.ok(new DatosDetalleForo(foro));
    }


    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionForo datos) {
        // Validar primero
        validadoresActualizacion.forEach(v -> v.validar(datos));

        // Luego actualizar
        var foro = foroRepository.getReferenceById(datos.id());
        foro.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleForo(foro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        validadorIdForoCorrecto.validar(id);
        var foro = foroRepository.findById(id).get();
        //borrado logico y no directo en la db porque inpide autorias de sistemas
        foro.desactivar();
        return ResponseEntity.ok("Foro desactivado correctamente");
    }

}
