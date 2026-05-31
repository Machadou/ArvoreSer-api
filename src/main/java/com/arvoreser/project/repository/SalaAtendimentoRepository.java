package com.arvoreser.project.repository;

import com.arvoreser.project.model.SalaAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaAtendimentoRepository extends JpaRepository<SalaAtendimento, Long> {
}
