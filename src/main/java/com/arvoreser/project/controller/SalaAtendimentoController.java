package com.arvoreser.project.controller;

import com.arvoreser.project.dto.SalaRequest;
import com.arvoreser.project.dto.SalaResponse;
import com.arvoreser.project.mapper.SalaMapper;
import com.arvoreser.project.model.SalaAtendimento;
import com.arvoreser.project.service.SalaAtendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaAtendimentoController {

    private final SalaAtendimentoService service;

    public SalaAtendimentoController(SalaAtendimentoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<SalaResponse>> listar() {
        return ResponseEntity.ok(
                service.listarTodos().stream()
                        .map(SalaMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(SalaMapper.toResponse(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<SalaResponse> criar(@Valid @RequestBody SalaRequest req) {
        SalaAtendimento novaEntidade = SalaMapper.toEntity(req);
        SalaAtendimento salva = service.salvar(novaEntidade);

        return ResponseEntity
                .created(URI.create("/salas/" + salva.getId()))
                .body(SalaMapper.toResponse(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody SalaRequest req) {
        SalaAtendimento dadosAtualizados = SalaMapper.toEntity(req);
        SalaAtendimento salva = service.atualizar(id, dadosAtualizados);

        return ResponseEntity.ok(SalaMapper.toResponse(salva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}