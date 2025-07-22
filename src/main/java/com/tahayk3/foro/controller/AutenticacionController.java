package com.tahayk3.foro.controller;


import com.tahayk3.foro.domain.usuario.DatosAutenticacionUsuario;
import com.tahayk3.foro.domain.usuario.Usuario;
import com.tahayk3.foro.infra.segurity.TokenService;
import com.tahayk3.foro.infra.segurity.DatosTokenJWT;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacionUsuario datos){
        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
            var autenticacion = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

            return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
