package ForoHub.Controller;

import ForoHub.Domain.usuario.Usuario;
import ForoHub.Services.DatosAutenticacion;
import ForoHub.Services.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DatosAutenticacion datos) {

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(datos.login(), datos.password());

        var authentication = manager.authenticate(authToken);

        var usuario = (Usuario) authentication.getPrincipal();
        var token = tokenService.generarToken(usuario);

        return ResponseEntity.ok(Map.of("token", token));
    }
}