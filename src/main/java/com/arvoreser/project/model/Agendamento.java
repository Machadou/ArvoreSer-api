package com.arvoreser.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoSessao;
    private LocalDateTime dataHora;

    // Relacionamentos reais mapeados no banco
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private SalaAtendimento sala;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoSessao() { return codigoSessao; }
    public void setCodigoSessao(String codigoSessao) { this.codigoSessao = codigoSessao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public SalaAtendimento getSala() { return sala; }
    public void setSala(SalaAtendimento sala) { this.sala = sala; }
}