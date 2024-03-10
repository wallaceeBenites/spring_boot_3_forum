package w.benites.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import w.benites.domain.usuario.DadosAutenticacao;
import w.benites.domain.usuario.Usuario;
import w.benites.infra.security.DadosTokenJWT;
import w.benites.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
       var authentication = manager.authenticate(authenticationtoken);

       var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

       return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }

}
