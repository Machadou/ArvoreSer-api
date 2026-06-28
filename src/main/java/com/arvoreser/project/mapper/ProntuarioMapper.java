package com.arvoreser.project.mapper;

import com.arvoreser.project.dto.ProntuarioResponse;
import com.arvoreser.project.model.Prontuario;

public final class ProntuarioMapper {

    private ProntuarioMapper() {}

    public static ProntuarioResponse toResponse(Prontuario p) {
        return new ProntuarioResponse(
                p.getId(),
                p.getPaciente().getId(),
                p.getPaciente().getNome(),
                p.getPsicologa().getId(),
                p.getPsicologa().getNome(),
                p.getPsicologa().getCrp(),
                p.getDataSessao(),
                p.getTextoEvolucao(),
                p.isFinalizado()
        );
    }
}