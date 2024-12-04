package com.school.manager.school_manager.dtos.alunos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlunoRequest {
  @NotBlank(message = "O nome é obrigatório.")
  @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
  private String nome;

  @NotBlank(message = "O e-mail é obrigatório.")
  private String email;

  @NotNull(message = "A data de nascimento é obrigatória.")
  private LocalDate dataNascimento;

  @NotNull(message = "O id da turma é obrigatório.")
  private Long turmaId;

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
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
  public Long getTurmaId() {
    return turmaId;
  }
  public void setTurmaId(Long turmaId) {
    this.turmaId = turmaId;
  }
}
