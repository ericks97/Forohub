package api.rest.forohub.controller;

import api.rest.forohub.dto.DatosAutenticacion;
import api.rest.forohub.dto.DatosTokenJWT;
import api.rest.forohub.model.Usuario;
import api.rest.forohub.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login (@RequestBody @Valid DatosAutenticacion dto){
        var authToken = new UsernamePasswordAuthenticationToken(dto.nombre(), dto.contrasena());
        var autenticacion = authenticationManager.authenticate(authToken);

        var tokenJWT = tokenService.getToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));


    }
}
