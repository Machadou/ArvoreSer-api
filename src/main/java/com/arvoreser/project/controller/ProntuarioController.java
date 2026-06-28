package com.arvoreser.project.controller;

import com.arvoreser.project.dto.ProntuarioRequest;
import com.arvoreser.project.dto.ProntuarioResponse;
import com.arvoreser.project.mapper.ProntuarioMapper;
import com.arvoreser.project.model.Prontuario;
import com.arvoreser.project.service.ProntuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final ProntuarioService service;

    public ProntuarioController(ProntuarioService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<ProntuarioResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos().stream().map(ProntuarioMapper::toResponse).toList());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ProntuarioResponse>> listarHistoricoPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(service.listarPorPaciente(pacienteId).stream().map(ProntuarioMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ProntuarioMapper.toResponse(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ProntuarioResponse> criar(@Valid @RequestBody ProntuarioRequest req) {
        Prontuario salvo = service.criar(req);
        return ResponseEntity.created(URI.create("/prontuarios/" + salvo.getId())).body(ProntuarioMapper.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ProntuarioRequest req) {
        return ResponseEntity.ok(ProntuarioMapper.toResponse(service.atualizar(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}