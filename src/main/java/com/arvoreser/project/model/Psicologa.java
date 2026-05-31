package com.arvoreser.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "psicologas")
public class Psicologa extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String crp;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCrp() { return crp; }
    public void setCrp(String crp) { this.crp = crp; }
}
