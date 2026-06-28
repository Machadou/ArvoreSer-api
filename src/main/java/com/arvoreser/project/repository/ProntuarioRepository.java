package com.arvoreser.project.repository;

import com.arvoreser.project.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    List<Prontuario> findByPaciente_IdOrderByDataSessaoDesc(Long pacienteId);
}