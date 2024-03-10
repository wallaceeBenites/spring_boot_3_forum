package w.benites.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import w.benites.domain.usuario.DadosListagemUsuario;
import w.benites.domain.usuario.Usuario;
import w.benites.domain.usuario.UsuarioRepository;
import w.benites.domain.usuario.*;

@RestController
@RequestMapping("usuario")
public class usuarioController {

    @Autowired
    private UsuarioRepository repository;


    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastrarUsuario dados, UriComponentsBuilder uriComponentsBuilder) {

        var usuario = new Usuario(dados);

        repository.save(usuario);


        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemUsuario(usuario));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUsuario::new);

        return ResponseEntity.ok(page);

    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {

        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){

        repository.deleteById(id);

        return  ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")

    public ResponseEntity detalhar(@PathVariable Long id){


        var usuario = repository.getReferenceById(id);

        return  ResponseEntity.ok(new DadosListagemUsuario(usuario));


    }

}