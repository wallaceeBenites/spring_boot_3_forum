package w.benites.domain.resposta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(
        @NotNull
        Long id,
        String mensagem,
        Long id_usuario
) {
}
