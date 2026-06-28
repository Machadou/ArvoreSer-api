package com.arvoreser.project.dto;

public record PsicologaResponse(
        Long id,
        String nome,
        String telefone,
        String crp
) {}