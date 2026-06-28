package com.arvoreser.project.controller;

import com.arvoreser.project.model.Psicologa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arvoreser.project.service.PsicologaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/psicologas")
public class PsicologaController {

    private final PsicologaService service;

    public PsicologaController(PsicologaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Psicologa>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Psicologa> buscar(@PathVariable Long id) {
        Psicologa psicologa = service.buscarPorId(id);
        return psicologa != null ? ResponseEntity.ok(psicologa) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Psicologa> criar(@RequestBody Psicologa psicologa) {
        Psicologa salva = service.salvar(psicologa);
        return ResponseEntity.created(URI.create("/psicologas/" + salva.getId())).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Psicologa> atualizar(@PathVariable Long id, @RequestBody Psicologa psicologa) {
        Psicologa atualizada = service.atualizar(id, psicologa);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Se o ID não existir, o serviço lança a exceção 404 e para a execução aqui
        service.deletar(id);

        // Se a linha acima rodou sem erros, deletou com sucesso (204 No Content)
        return ResponseEntity.noContent().build();
    }
}