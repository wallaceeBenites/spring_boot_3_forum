package w.benites.resposta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarRespota(
        // >> DTO "Data Transfer Object". É um padrão de design usado para transferir dados
        @NotBlank
        String mensagem,
        @NotNull
        Long id_usuario
) {
}
