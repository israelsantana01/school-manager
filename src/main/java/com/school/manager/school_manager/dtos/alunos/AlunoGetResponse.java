package com.school.manager.school_manager.dtos.alunos;

import java.time.LocalDate;

import com.school.manager.school_manager.helper.EntidadeResumida;

public class AlunoGetResponse {
  private Long id;
  private String nome;
  private String matricula;
  private String email;
  private LocalDate dataNascimento;
  private EntidadeResumida turma;

  public AlunoGetResponse(Long id, String nome, String matricula, String email, LocalDate dataNascimento, EntidadeResumida turma) {
    this.id = id;
    this.nome = nome;
    this.matricula = matricula;
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
  public EntidadeResumida getTurma() {
    return turma;
  }
  public void setTurma(EntidadeResumida turma) {
    this.turma = turma;
  }
}
