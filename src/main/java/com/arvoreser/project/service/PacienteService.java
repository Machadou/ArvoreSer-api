package com.arvoreser.project.service;

import com.arvoreser.project.model.Paciente;
import com.arvoreser.project.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) { this.repo = repo; }

    public List<Paciente> listarTodos() { return repo.findAll(); }

    public Paciente buscarPorId(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public Paciente salvar(Paciente paciente) { return repo.save(paciente); }

    @Transactional
    public Paciente atualizar(Long id, Paciente atualizado) {
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