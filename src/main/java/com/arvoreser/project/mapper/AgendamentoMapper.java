package com.arvoreser.project.mapper;

import com.arvoreser.project.dto.AgendamentoResponse;
import com.arvoreser.project.model.Agendamento;

public final class AgendamentoMapper {

    private AgendamentoMapper() {}

    public static AgendamentoResponse toResponse(Agendamento a) {
        return new AgendamentoResponse(
                a.getId(),
                a.getCodigoSessao(),
                a.getDataHora(),
                a.getPaciente() != null ? a.getPaciente().getId() : null,
                a.getPaciente() != null ? a.getPaciente().getNome() : null,
                a.getSala() != null ? a.getSala().getId() : null,
                a.getSala() != null ? a.getSala().getNomeSala() : null
        );
    }
}