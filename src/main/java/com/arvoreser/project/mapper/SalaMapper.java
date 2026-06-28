package com.arvoreser.project.mapper;

import com.arvoreser.project.dto.SalaRequest;
import com.arvoreser.project.dto.SalaResponse;
import com.arvoreser.project.model.SalaAtendimento;

public final class SalaMapper {

    private SalaMapper() {}

    public static SalaAtendimento toEntity(SalaRequest dto) {
        SalaAtendimento s = new SalaAtendimento();
        s.setNomeSala(dto.nomeSala());
        s.setCapacidadeMaxima(dto.capacidadeMaxima());
        return s;
    }

    public static SalaResponse toResponse(SalaAtendimento s) {
        return new SalaResponse(s.getId(), s.getNomeSala(), s.getCapacidadeMaxima());
    }
}