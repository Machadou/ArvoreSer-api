package com.arvoreser.project.dto;

public record PacienteResponse(
        Long id,
        String nome,
        String telefone,
        String cpf
) {}