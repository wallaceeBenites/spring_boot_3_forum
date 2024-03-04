package w.benites.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import w.benites.topico.*;

import java.util.List;

@RestController
@RequestMapping("topicos")
public class topicoController {

    @Autowired
    private TopicoRepository repository;                                     // interface para pode acessar o banco de dados

    @PostMapping                                                                // Recebe e requisição via método Post
    public void abrirTopico(@RequestBody @Valid DadosAbrirTopico dados) {        // Recebe do formulário front-end em formato JSON e através da class
        //  DadosAbrirTopico é convertido em String separadas

        repository.save(new Topico(dados));   //  Através da instância da interface do tipo TopicoRepository que estende JpaRepository
        // Que herda métodos SQL para salvar no banco de dados os dados recebidos do método POST
    }

   @GetMapping
   public Page<DadosListagemTopico> listar(@PageableDefault(size = 10) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemTopico::new);
    }

   @PutMapping
   @Transactional
   public void atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){

        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);

    }

   @DeleteMapping("/{id}")
   @Transactional
   public void excluir(@PathVariable Long id){

        repository.deleteById(id);

    }

}
