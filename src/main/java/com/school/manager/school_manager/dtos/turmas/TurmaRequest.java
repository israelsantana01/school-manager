package com.school.manager.school_manager.dtos.turmas;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TurmaRequest {
  @NotBlank(message = "O nome é obrigatório.")
  @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
  private String nome;

  @Min(2024)
  private int ano;

  private List<Long> alunosIds;
  public List<Long> getAlunosIds() {
    return alunosIds;
  }

  public void setAlunosIds(List<Long> alunosIds) {
    this.alunosIds = alunosIds;
  }

  private List<Long> professoresIds;
  public List<Long> getProfessoresIds() {
    return professoresIds;
  }

  public void setProfessoresIds(List<Long> professoresIds) {
    this.professoresIds = professoresIds;
  }

  private List<Long> disciplinasIds;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public List<Long> getDisciplinasIds() {
    return disciplinasIds;
  }

  public void setDisciplinasIds(List<Long> disciplinasIds) {
    this.disciplinasIds = disciplinasIds;
  }

}
