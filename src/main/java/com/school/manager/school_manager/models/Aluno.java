package com.school.manager.school_manager.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aluno")
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String matricula;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private LocalDate dataNascimento;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "turma_id", nullable = false)
  private Turma turma;

  public Aluno() {}

  public Aluno(String nome, String matricula, String email, LocalDate dataNascimento, Turma turma) {
    this.nome = nome;
    this.matricula = matricula;
    this.email = email;
    this.dataNascimento = dataNascimento;
    this.turma = turma;
  }

  public Aluno(Long id, String matricula, String nome, String email, LocalDate dataNascimento, Turma turma) {
    this.id = id;
    this.matricula = matricula;
    this.nome = nome;
    this.email = email;
    this.dataNascimento = dataNascimento;
    this.turma = turma;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Turma getTurma() {
    return turma;
  }

  public void setTurmas(Turma turma) {
    this.turma = turma;
  }
}
