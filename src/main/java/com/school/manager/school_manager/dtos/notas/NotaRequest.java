package com.school.manager.school_manager.dtos.notas;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NotaRequest {
  @NotNull(message = "O valor da nota é obrigatório.")
  @Size(min = 0, max = 10, message = "O valor da nota deve ser entre 0 e 10.")
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
