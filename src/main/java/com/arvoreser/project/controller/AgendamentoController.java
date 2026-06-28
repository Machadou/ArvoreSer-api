package com.arvoreser.project.controller;

import com.arvoreser.project.dto.AgendamentoRequest;
import com.arvoreser.project.dto.AgendamentoResponse;
import com.arvoreser.project.mapper.AgendamentoMapper;
import com.arvoreser.project.model.Agendamento;
import com.arvoreser.project.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos().stream()
                .map(AgendamentoMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(AgendamentoMapper.toResponse(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criar(@Valid @RequestBody AgendamentoRequest req) {
        Agendamento salvo = service.criar(req);
        return ResponseEntity
                .created(URI.create("/agendamentos/" + salvo.getId()))
                .body(AgendamentoMapper.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable Long id,
                                                         @Valid @RequestBody AgendamentoRequest req) {
        Agendamento atualizado = service.atualizar(id, req);
        return ResponseEntity.ok(AgendamentoMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}