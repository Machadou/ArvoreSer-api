package com.arvoreser.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public record ProntuarioRequest(
        @NotNull(message = "O ID do paciente é obrigatório")
        Long pacienteId,

        @NotNull(message = "O ID da psicóloga é obrigatório")
        Long psicologaId,

        @NotNull(message = "A data da sessão é obrigatória")
        @PastOrPresent(message = "Uma sessão clínica não pode ser registrada numa data futura")
        LocalDateTime dataSessao,

        @NotBlank(message = "O texto da evolução clínica não pode estar vazio")
        String textoEvolucao,

        boolean finalizar
) {}