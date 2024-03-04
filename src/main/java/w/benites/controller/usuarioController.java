package w.benites.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import w.benites.topico.DadosAtualizacaoTopico;
import w.benites.topico.DadosListagemTopico;
import w.benites.usuario.*;

@RestController
@RequestMapping("usuario")
public class usuarioController {

    @Autowired
    private UsuarioRepository repository;


    @PostMapping
    public void cadastrarUsuario(@RequestBody @Valid DadosCadastrarUsuario dados) {

        repository.save(new Usuario(dados));

    }

    @GetMapping
    public Page<DadosListagemUsuario> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemUsuario::new);
    }


    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {

        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){

        repository.deleteById(id);

    }

}