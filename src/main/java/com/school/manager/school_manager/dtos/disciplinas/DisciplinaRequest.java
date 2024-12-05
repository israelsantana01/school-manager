package com.school.manager.school_manager.dtos.disciplinas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DisciplinaRequest {
  @NotBlank(message = "O nome é obrigatório.")
  @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
  private String nome;

  @NotNull(message = "A carga horária é obrigatória.")
  private int cargaHoraria;

  @NotNull(message = "O id do(a) professor(a) é obrigatório.")
  private Long professorId;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getCargaHoraria() {
    return cargaHoraria;
  }

  public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
  }

  public Long getProfessorId() {
    return professorId;
  }

  public void setProfessorId(Long professorId) {
    this.professorId = professorId;
  }

}
