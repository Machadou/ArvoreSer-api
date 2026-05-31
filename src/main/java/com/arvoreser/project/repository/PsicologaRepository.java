package com.arvoreser.project.repository;

import com.arvoreser.project.model.Psicologa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsicologaRepository extends JpaRepository<Psicologa, Long> {
}
