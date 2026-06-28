package com.arvoreser.project.service;

import com.arvoreser.project.dto.AgendamentoRequest;
import com.arvoreser.project.exception.BusinessException;
import com.arvoreser.project.exception.ResourceNotFoundException;
import com.arvoreser.project.model.Agendamento;
import com.arvoreser.project.model.Paciente;
import com.arvoreser.project.model.SalaAtendimento;
import com.arvoreser.project.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repo;
    private final PacienteService pacienteService;
    private final SalaAtendimentoService salaService;

    public AgendamentoService(AgendamentoRepository repo,
                              PacienteService pacienteService,
                              SalaAtendimentoService salaService) {
        this.repo = repo;
        this.pacienteService = pacienteService;
        this.salaService = salaService;
    }

    @Transactional(readOnly = true)
    public List<Agendamento> listarTodos() { return repo.findAll(); }

    @Transactional(readOnly = true)
    public Agendamento buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado: id=" + id));
    }

    @Transactional
    public Agendamento criar(AgendamentoRequest req) {
        // valida existência das FKs (lança 404 se não existir)
        Paciente paciente = pacienteService.buscarPorId(req.pacienteId());
        SalaAtendimento sala = salaService.buscarPorId(req.salaId());

        // regra de negócio: sem conflito de sala nem de paciente no mesmo horário
        validarConflitos(req.salaId(), req.pacienteId(), req.dataHora(), null);

        Agendamento a = new Agendamento();
        a.setCodigoSessao(req.codigoSessao());
        a.setDataHora(req.dataHora());
        a.setPaciente(paciente);
        a.setSala(sala);
        return repo.save(a);
    }

    @Transactional
    public Agendamento atualizar(Long id, AgendamentoRequest req) {
        Agendamento existente = buscarPorId(id);

        Paciente paciente = pacienteService.buscarPorId(req.pacienteId());
        SalaAtendimento sala = salaService.buscarPorId(req.salaId());

        // ignora o próprio agendamento na verificação de conflito
        validarConflitos(req.salaId(), req.pacienteId(), req.dataHora(), id);

        existente.setCodigoSessao(req.codigoSessao());
        existente.setDataHora(req.dataHora());
        existente.setPaciente(paciente);
        existente.setSala(sala);
        return repo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado: id=" + id);
        }
        repo.deleteById(id);
    }

    private void validarConflitos(Long salaId, Long pacienteId,
                                  java.time.LocalDateTime dataHora, Long idIgnorar) {
        boolean salaOcupada = (idIgnorar == null)
                ? repo.existsBySala_IdAndDataHora(salaId, dataHora)
                : repo.existsBySala_IdAndDataHoraAndIdNot(salaId, dataHora, idIgnorar);
        if (salaOcupada) {
            throw new BusinessException("Já existe um agendamento para esta sala neste horário.");
        }

        boolean pacienteOcupado = (idIgnorar == null)
                ? repo.existsByPaciente_IdAndDataHora(pacienteId, dataHora)
                : repo.existsByPaciente_IdAndDataHoraAndIdNot(pacienteId, dataHora, idIgnorar);
        if (pacienteOcupado) {
            throw new BusinessException("Este paciente já possui um agendamento neste horário.");
        }
    }
}