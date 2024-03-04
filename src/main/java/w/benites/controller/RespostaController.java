package w.benites.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import w.benites.resposta.*;
import w.benites.usuario.*;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @PostMapping
    public void criarResposta(@RequestBody @Valid DadosCriarRespota dados){

        repository.save(new Resposta(dados));

    }

    @GetMapping
    public Page<DadosListagemResposta> listar(@PageableDefault(size = 10) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemResposta::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoResposta dados) {

        var resposta = repository.getReferenceById(dados.id());
        resposta.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){

        repository.deleteById(id);

    }


}
