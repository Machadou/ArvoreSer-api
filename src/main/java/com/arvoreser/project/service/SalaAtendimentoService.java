package com.arvoreser.project.service;

import com.arvoreser.project.model.SalaAtendimento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arvoreser.project.repository.SalaAtendimentoRepository;

import java.util.List;

@Service
public class SalaAtendimentoService {

    private final SalaAtendimentoRepository repo;

    public SalaAtendimentoService(SalaAtendimentoRepository repo) { this.repo = repo; }

    public List<SalaAtendimento> listarTodos() { return repo.findAll(); }

    public SalaAtendimento buscarPorId(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public SalaAtendimento salvar(SalaAtendimento sala) { return repo.save(sala); }

    @Transactional
    public SalaAtendimento atualizar(Long id, SalaAtendimento atualizada) {
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