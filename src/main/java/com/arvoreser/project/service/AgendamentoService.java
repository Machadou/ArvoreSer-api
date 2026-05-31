package com.arvoreser.project.service;

import com.arvoreser.project.model.Agendamento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arvoreser.project.repository.AgendamentoRepository;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repo;

    public AgendamentoService(AgendamentoRepository repo) { this.repo = repo; }

    public List<Agendamento> listarTodos() { return repo.findAll(); }

    public Agendamento buscarPorId(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public Agendamento salvar(Agendamento agendamento) { return repo.save(agendamento); }

    @Transactional
    public Agendamento atualizar(Long id, Agendamento atualizado) {
        if (!repo.existsById(id)) return null;
        atualizado.setId(id);
        return repo.save(atualizado);
    }

    @Transactional
    public boolean deletar(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}