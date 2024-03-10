package w.benites.domain.resposta;

public record DadosListagemResposta(
        Long id,
        String mensagem,
       Long id_usuario
) {

    public DadosListagemResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getId_usuario());

    }


}
