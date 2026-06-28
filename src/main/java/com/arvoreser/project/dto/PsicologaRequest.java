package com.arvoreser.project.dto;

import jakarta.validation.constraints.NotBlank;

public record PsicologaRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String telefone,

        @NotBlank(message = "CRP é obrigatório")
        String crp
) {}