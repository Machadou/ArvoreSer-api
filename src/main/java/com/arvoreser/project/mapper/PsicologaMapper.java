package com.arvoreser.project.mapper;

import com.arvoreser.project.dto.PsicologaRequest;
import com.arvoreser.project.dto.PsicologaResponse;
import com.arvoreser.project.model.Psicologa;

public final class PsicologaMapper {

    private PsicologaMapper() {}

    public static Psicologa toEntity(PsicologaRequest dto) {
        Psicologa p = new Psicologa();
        p.setNome(dto.nome());
        p.setTelefone(dto.telefone());
        p.setCrp(dto.crp());
        return p;
    }

    public static PsicologaResponse toResponse(Psicologa p) {
        return new PsicologaResponse(p.getId(), p.getNome(), p.getTelefone(), p.getCrp());
    }
}