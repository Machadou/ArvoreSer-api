package com.arvoreser.project.model;

import com.arvoreser.project.converter.CryptoConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "psicologa_id", nullable = false)
    private Psicologa psicologa;

    @Column(nullable = false)
    private LocalDateTime dataSessao;

    @Convert(converter = CryptoConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String textoEvolucao;

    @Column(nullable = false)
    private boolean finalizado = false;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Psicologa getPsicologa() { return psicologa; }
    public void setPsicologa(Psicologa psicologa) { this.psicologa = psicologa; }
    public LocalDateTime getDataSessao() { return dataSessao; }
    public void setDataSessao(LocalDateTime dataSessao) { this.dataSessao = dataSessao; }
    public String getTextoEvolucao() { return textoEvolucao; }
    public void setTextoEvolucao(String textoEvolucao) { this.textoEvolucao = textoEvolucao; }
    public boolean isFinalizado() { return finalizado; }
    public void setFinalizado(boolean finalizado) { this.finalizado = finalizado; }
}