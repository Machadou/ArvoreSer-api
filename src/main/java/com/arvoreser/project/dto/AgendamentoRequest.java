package com.arvoreser.project.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        @NotBlank(message = "Código da sessão é obrigatório")
        String codigoSessao,

        @NotNull(message = "Data/hora é obrigatória")
        @Future(message = "O agendamento deve ser para uma data futura")
        LocalDateTime dataHora,

        @NotNull(message = "Paciente é obrigatório")
        Long pacienteId,

        @NotNull(message = "Sala é obrigatória")
        Long salaId
) {}