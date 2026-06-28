package com.arvoreser.project.service;

import com.arvoreser.project.exception.ResourceNotFoundException;
import com.arvoreser.project.model.SalaAtendimento;
import com.arvoreser.project.repository.SalaAtendimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaAtendimentoService {

    private final SalaAtendimentoRepository repo;

    public SalaAtendimentoService(SalaAtendimentoRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true)
    public List<SalaAtendimento> listarTodos() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public SalaAtendimento buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada: id=" + id));
    }

    @Transactional
    public SalaAtendimento salvar(SalaAtendimento sala) { return repo.save(sala); }

    @Transactional
    public SalaAtendimento atualizar(Long id, SalaAtendimento dados) {
        SalaAtendimento existente = buscarPorId(id);
        if (dados.getNomeSala() != null)     existente.setNomeSala(dados.getNomeSala());
        if (dados.getCapacidadeMaxima() > 0) existente.setCapacidadeMaxima(dados.getCapacidadeMaxima());
        return repo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Sala não encontrada: id=" + id);
        }
        repo.deleteById(id);
    }
}