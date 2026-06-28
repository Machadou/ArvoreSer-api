package com.arvoreser.project.controller;

import com.arvoreser.project.dto.PacienteRequest;
import com.arvoreser.project.dto.PacienteResponse;
import com.arvoreser.project.mapper.PacienteMapper;
import com.arvoreser.project.model.Paciente;
import com.arvoreser.project.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar() {
        return ResponseEntity.ok(
                service.listarTodos().stream()
                        .map(PacienteMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(PacienteMapper.toResponse(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@Valid @RequestBody PacienteRequest req) {
        Paciente novaEntidade = PacienteMapper.toEntity(req);
        Paciente salvo = service.salvar(novaEntidade);

        return ResponseEntity
                .created(URI.create("/pacientes/" + salvo.getId()))
                .body(PacienteMapper.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest req) {
        Paciente dadosAtualizados = PacienteMapper.toEntity(req);
        Paciente salvo = service.atualizar(id, dadosAtualizados);

        return ResponseEntity.ok(PacienteMapper.toResponse(salvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}