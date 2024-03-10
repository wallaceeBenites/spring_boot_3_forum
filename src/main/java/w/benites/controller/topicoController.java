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
import w.benites.domain.topico.DadosListagemTopico;
import w.benites.domain.topico.Topico;
import w.benites.domain.topico.TopicoRepository;
import w.benites.domain.topico.*;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class topicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity abrirTopico(@RequestBody @Valid DadosAbrirTopico dados, UriComponentsBuilder uriComponentsBuilder) {

        var topico = new Topico(dados);

        repository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalharTopico(topico));


    }

   @GetMapping
   public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);

       return ResponseEntity.ok(page);
    }

   @PutMapping
   @Transactional
   public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){

        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);

       return ResponseEntity.ok(new DadosDetalharTopico(topico));

    }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity excluir(@PathVariable Long id){

        repository.deleteById(id);

       return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")

    public ResponseEntity detalhar(@PathVariable Long id){


        var topico = repository.getReferenceById(id);

        return  ResponseEntity.ok(new DadosDetalharTopico(topico));


    }

}
