package com.arvoreser.project.service;

import com.arvoreser.project.model.Psicologa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arvoreser.project.repository.PsicologaRepository;

import java.util.List;

@Service
public class PsicologaService {

    private final PsicologaRepository repo;

    public PsicologaService(PsicologaRepository repo) { this.repo = repo; }

    public List<Psicologa> listarTodos() { return repo.findAll(); }

    public Psicologa buscarPorId(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public Psicologa salvar(Psicologa psicologa) { return repo.save(psicologa); }

    @Transactional
    public Psicologa atualizar(Long id, Psicologa atualizada) {
        if (!repo.existsById(id)) return null;
        atualizada.setId(id);
        return repo.save(atualizada);
    }

    @Transactional
    public boolean deletar(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}