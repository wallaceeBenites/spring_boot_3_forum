package w.benites.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import w.benites.domain.resposta.DadosListagemResposta;
import w.benites.domain.resposta.Resposta;
import w.benites.domain.resposta.RespostaRepository;
import w.benites.domain.resposta.*;


@RestController
@RequestMapping("respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @PostMapping
    public ResponseEntity criarResposta(@RequestBody @Valid DadosCriarRespota dados, UriComponentsBuilder uriComponentsBuilder){

        var resposta = new Resposta(dados);
        repository.save(resposta);

        var uri = uriComponentsBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemResposta(resposta));


    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemResposta>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemResposta::new);

        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoResposta dados) {

        var resposta = repository.getReferenceById(dados.id());
        resposta.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemResposta(resposta));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){

        repository.deleteById(id);

        return  ResponseEntity.noContent().build();


    }

    @GetMapping("/{id}")

    public ResponseEntity detalhar(@PathVariable Long id){


        var resposta = repository.getReferenceById(id);

        return  ResponseEntity.ok(new DadosListagemResposta(resposta));


    }


}
