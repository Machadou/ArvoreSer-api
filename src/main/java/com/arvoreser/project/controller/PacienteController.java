package com.arvoreser.project.controller;

import com.arvoreser.project.model.Paciente;
import com.arvoreser.project.service.PacienteService;
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
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        Paciente paciente = service.buscarPorId(id);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Paciente> criar(@RequestBody Paciente paciente) {
        Paciente salvo = service.salvar(paciente);
        return ResponseEntity.created(URI.create("/pacientes/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente atualizado = service.atualizar(id, paciente);
        return atualizado != null ? ResponseEntity.ok(atualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}