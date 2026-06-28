package com.arvoreser.project.service;

import com.arvoreser.project.exception.ResourceNotFoundException;
import com.arvoreser.project.model.Paciente;
import com.arvoreser.project.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: id=" + id));
    }

    @Transactional
    public Paciente salvar(Paciente paciente) { return repo.save(paciente); }

    @Transactional
    public Paciente atualizar(Long id, Paciente dados) {
        Paciente existente = buscarPorId(id); // já lança 404 se não existir
        // merge parcial: só sobrescreve o que veio preenchido
        if (dados.getNome() != null)     existente.setNome(dados.getNome());
        if (dados.getTelefone() != null) existente.setTelefone(dados.getTelefone());
        if (dados.getCpf() != null)      existente.setCpf(dados.getCpf());
        return repo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado: id=" + id);
        }
        repo.deleteById(id);
    }
}