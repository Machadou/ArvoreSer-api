package com.arvoreser.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // 1. Importar o pacote de validação

@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O CPF é obrigatório e não pode estar em branco")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 123.456.789-00")
    @Column(unique = true, nullable = false)
    private String cpf;


    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}