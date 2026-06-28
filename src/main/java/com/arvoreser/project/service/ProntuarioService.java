package com.arvoreser.project.service;

import com.arvoreser.project.dto.ProntuarioRequest;
import com.arvoreser.project.exception.BusinessException;
import com.arvoreser.project.exception.ResourceNotFoundException;
import com.arvoreser.project.model.Paciente;
import com.arvoreser.project.model.Prontuario;
import com.arvoreser.project.model.Psicologa;
import com.arvoreser.project.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProntuarioService {

    private final ProntuarioRepository repo;
    private final PacienteService pacienteService;
    private final PsicologaService psicologaService;

    public ProntuarioService(ProntuarioRepository repo, PacienteService pacienteService, PsicologaService psicologaService) {
        this.repo = repo;
        this.pacienteService = pacienteService;
        this.psicologaService = psicologaService;
    }

    @Transactional(readOnly = true)
    public List<Prontuario> listarTodos() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public List<Prontuario> listarPorPaciente(Long pacienteId) {
        pacienteService.buscarPorId(pacienteId); // Garante que o paciente existe
        return repo.findByPaciente_IdOrderByDataSessaoDesc(pacienteId);
    }

    @Transactional(readOnly = true)
    public Prontuario buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado: id=" + id));
    }

    @Transactional
    public Prontuario criar(ProntuarioRequest req) {
        Paciente paciente = pacienteService.buscarPorId(req.pacienteId());
        Psicologa psicologa = psicologaService.buscarPorId(req.psicologaId());

        Prontuario p = new Prontuario();
        p.setPaciente(paciente);
        p.setPsicologa(psicologa);
        p.setDataSessao(req.dataSessao());
        p.setTextoEvolucao(req.textoEvolucao());
        p.setFinalizado(req.finalizar());

        return repo.save(p);
    }

    @Transactional
    public Prontuario atualizar(Long id, ProntuarioRequest req) {
        Prontuario existente = buscarPorId(id);

        // === TRAVA JURÍDICA DO CFP ===
        if (existente.isFinalizado()) {
            throw new BusinessException("Conformidade CFP: Este prontuário já foi assinado e finalizado. A edição de evoluções clínicas passadas é estritamente proibida por lei.");
        }

        Paciente paciente = pacienteService.buscarPorId(req.pacienteId());
        Psicologa psicologa = psicologaService.buscarPorId(req.psicologaId());

        existente.setPaciente(paciente);
        existente.setPsicologa(psicologa);
        existente.setDataSessao(req.dataSessao());
        existente.setTextoEvolucao(req.textoEvolucao());
        existente.setFinalizado(req.finalizar());

        return repo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        Prontuario existente = buscarPorId(id);

        // === TRAVA JURÍDICA DO CFP ===
        if (existente.isFinalizado()) {
            throw new BusinessException("Conformidade CFP: Documentos de prontuário já finalizados não podem ser apagados do histórico médico do paciente.");
        }

        repo.delete(existente);
    }
}