package com.arvoreser.project.service;

import com.arvoreser.project.exception.ResourceNotFoundException;
import com.arvoreser.project.model.Psicologa;
import com.arvoreser.project.repository.PsicologaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PsicologaService {

    private final PsicologaRepository repo;

    public PsicologaService(PsicologaRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true)
    public List<Psicologa> listarTodos() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public Psicologa buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psicóloga não encontrada: id=" + id));
    }

    @Transactional
    public Psicologa salvar(Psicologa psicologa) { return repo.save(psicologa); }

    @Transactional
    public Psicologa atualizar(Long id, Psicologa dados) {
        Psicologa existente = buscarPorId(id);
        if (dados.getNome() != null)     existente.setNome(dados.getNome());
        if (dados.getTelefone() != null) existente.setTelefone(dados.getTelefone());
        if (dados.getCrp() != null)      existente.setCrp(dados.getCrp());
        return repo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Psicóloga não encontrada: id=" + id);
        }
        repo.deleteById(id);
    }
}