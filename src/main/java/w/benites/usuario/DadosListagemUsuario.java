package w.benites.usuario;

import w.benites.topico.Topico;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String email,
        String senha,
        String data_abertura_conta
) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getData_abertura_conta());

    }
}
