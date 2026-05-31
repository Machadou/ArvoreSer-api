package com.arvoreser.project.controller;

import com.arvoreser.project.model.Agendamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arvoreser.project.service.AgendamentoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscar(@PathVariable Long id) {
        Agendamento agendamento = service.buscarPorId(id);
        return agendamento != null ? ResponseEntity.ok(agendamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
        Agendamento salvo = service.salvar(agendamento);
        return ResponseEntity.created(URI.create("/agendamentos/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Agendamento atualizado = service.atualizar(id, agendamento);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}