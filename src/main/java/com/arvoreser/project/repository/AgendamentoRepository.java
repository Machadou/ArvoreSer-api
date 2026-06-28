package com.arvoreser.project.repository;

import com.arvoreser.project.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Conflito: mesma sala no mesmo horário
    boolean existsBySala_IdAndDataHora(Long salaId, LocalDateTime dataHora);

    // Conflito: mesmo paciente no mesmo horário
    boolean existsByPaciente_IdAndDataHora(Long pacienteId, LocalDateTime dataHora);

    // Versões que ignoram o próprio registro (úteis no update)
    boolean existsBySala_IdAndDataHoraAndIdNot(Long salaId, LocalDateTime dataHora, Long id);

    boolean existsByPaciente_IdAndDataHoraAndIdNot(Long pacienteId, LocalDateTime dataHora, Long id);
}