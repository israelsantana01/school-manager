package com.school.manager.school_manager.dtos.professores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfessorRequest {
  @NotBlank(message = "O nome é obrigatório.")
  @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
  private String nome;

  @NotBlank(message = "O e-mail é obrigatório.")
  private String email;

  @NotBlank(message = "A disciplina principal é obrigatória")
  private String disciplinaPrincipal;

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

  public String getDisciplinaPrincipal() {
    return disciplinaPrincipal;
  }

  public void setDisciplinaPrincipal(String disciplinaPrincipal) {
    this.disciplinaPrincipal = disciplinaPrincipal;
  }

}
