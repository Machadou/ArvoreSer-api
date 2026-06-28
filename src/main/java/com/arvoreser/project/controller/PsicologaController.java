package com.arvoreser.project.controller;

import com.arvoreser.project.dto.PsicologaRequest;
import com.arvoreser.project.dto.PsicologaResponse;
import com.arvoreser.project.mapper.PsicologaMapper;
import com.arvoreser.project.model.Psicologa;
import com.arvoreser.project.service.PsicologaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/psicologas")
public class PsicologaController {

    private final PsicologaService service;

    public PsicologaController(PsicologaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<PsicologaResponse>> listar() {
        return ResponseEntity.ok(
                service.listarTodos().stream()
                        .map(PsicologaMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicologaResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(PsicologaMapper.toResponse(service.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<PsicologaResponse> criar(@Valid @RequestBody PsicologaRequest req) {
        Psicologa novaEntidade = PsicologaMapper.toEntity(req);
        Psicologa salva = service.salvar(novaEntidade);

        return ResponseEntity
                .created(URI.create("/psicologas/" + salva.getId()))
                .body(PsicologaMapper.toResponse(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsicologaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PsicologaRequest req) {
        Psicologa dadosAtualizados = PsicologaMapper.toEntity(req);
        Psicologa salva = service.atualizar(id, dadosAtualizados);

        return ResponseEntity.ok(PsicologaMapper.toResponse(salva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}