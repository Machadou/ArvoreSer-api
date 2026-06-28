package com.arvoreser.project.controller;

import com.arvoreser.project.model.SalaAtendimento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arvoreser.project.service.SalaAtendimentoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaAtendimentoController {

    private final SalaAtendimentoService service;

    public SalaAtendimentoController(SalaAtendimentoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<SalaAtendimento>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaAtendimento> buscar(@PathVariable Long id) {
        SalaAtendimento sala = service.buscarPorId(id);
        return sala != null ? ResponseEntity.ok(sala) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SalaAtendimento> criar(@RequestBody SalaAtendimento sala) {
        SalaAtendimento salva = service.salvar(sala);
        return ResponseEntity.created(URI.create("/salas/" + salva.getId())).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaAtendimento> atualizar(@PathVariable Long id, @RequestBody SalaAtendimento sala) {
        SalaAtendimento atualizada = service.atualizar(id, sala);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Mesma lógica de encapsulamento: a verificação de existência fica no Service
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}