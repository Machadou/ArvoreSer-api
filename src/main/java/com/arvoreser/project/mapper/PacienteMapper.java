package com.arvoreser.project.mapper;

import com.arvoreser.project.dto.PacienteRequest;
import com.arvoreser.project.dto.PacienteResponse;
import com.arvoreser.project.model.Paciente;

public final class PacienteMapper {

    private PacienteMapper() {}

    public static Paciente toEntity(PacienteRequest dto) {
        Paciente p = new Paciente();
        p.setNome(dto.nome());
        p.setTelefone(dto.telefone());
        p.setCpf(dto.cpf());
        return p;
    }

    public static PacienteResponse toResponse(Paciente p) {
        return new PacienteResponse(p.getId(), p.getNome(), p.getTelefone(), p.getCpf());
    }
}