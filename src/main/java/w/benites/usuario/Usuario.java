package w.benites.usuario;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import w.benites.topico.DadosAbrirTopico;
import w.benites.topico.DadosAtualizacaoTopico;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Mapeia a tabela topicos do banco de dados
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String data_abertura_conta;

    public Usuario(DadosCadastrarUsuario dados) {    // Atribui os dado recebido para colunas do banco de dados


        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.data_abertura_conta = java.time.LocalDate.now().toString();



    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {

        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.email() != null){
            this.email = dados.email();
        }

        if(dados.senha() != null){
            this.senha = dados.senha();
        }

    }


}
