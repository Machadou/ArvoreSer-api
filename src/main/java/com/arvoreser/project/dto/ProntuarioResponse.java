package com.arvoreser.project.dto;

import java.time.LocalDateTime;

public record ProntuarioResponse(
        Long id,
        Long pacienteId,
        String nomePaciente,
        Long psicologaId,
        String nomePsicologa,
        String crpPsicologa,
        LocalDateTime dataSessao,
        String textoEvolucao,
        boolean finalizado
) {}