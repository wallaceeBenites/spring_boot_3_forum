package w.benites.resposta;

import w.benites.usuario.Usuario;

public record DadosListagemResposta(
        Long id,
        String mensagem,
       Long id_usuario
) {

    public DadosListagemResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getId_usuario());

    }


}
