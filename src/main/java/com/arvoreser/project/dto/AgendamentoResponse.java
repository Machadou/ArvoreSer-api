package com.arvoreser.project.dto;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        String codigoSessao,
        LocalDateTime dataHora,
        Long pacienteId,
        String pacienteNome,
        Long salaId,
        String salaNome
) {}