package com.school.manager.school_manager.dtos.notas;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class NotaRequest {
  @Min(0)
  @Max(10)
  private double valor;

  @NotNull(message = "A data de avaliação é obrigatória.")
  private LocalDate dataAvaliacao;

  @NotNull(message = "O id do aluno é obrigatório.")
  private Long alunoId;

  @NotNull(message = "O id da disciplina é obrigatório.")
  private Long disciplinaId;

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public LocalDate getDataAvaliacao() {
    return dataAvaliacao;
  }

  public void setDataAvaliacao(LocalDate dataAvaliacao) {
    this.dataAvaliacao = dataAvaliacao;
  }

  public Long getAlunoId() {
    return alunoId;
  }

  public void setAlunoId(Long alunoId) {
    this.alunoId = alunoId;
  }

  public Long getDisciplinaId() {
    return disciplinaId;
  }

  public void setDisciplinaId(Long disciplinaId) {
    this.disciplinaId = disciplinaId;
  }
}
