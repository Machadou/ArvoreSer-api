 package com.arvoreser.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record SalaRequest(
        @NotBlank(message = "Nome da sala é obrigatório")
        String nomeSala,

        @Positive(message = "Capacidade máxima deve ser maior que zero")
        int capacidadeMaxima
) {}

