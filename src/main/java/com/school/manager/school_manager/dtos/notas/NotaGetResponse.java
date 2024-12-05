package com.school.manager.school_manager.dtos.notas;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.manager.school_manager.helper.EntidadeResumida;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class NotaGetResponse {
  private Long id;

  private double valor;

  private LocalDate dataAvaliacao;

  private EntidadeResumida aluno;

  private EntidadeResumida disciplina;

  public NotaGetResponse(Long id, double valor, LocalDate dataAvaliacao, EntidadeResumida aluno, EntidadeResumida disciplina) {
    this.id = id;
    this.valor = valor;
    this.dataAvaliacao = dataAvaliacao;
    this.aluno = aluno;
    this.disciplina = disciplina;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public EntidadeResumida getAluno() {
    return aluno;
  }

  public void setAluno(EntidadeResumida aluno) {
    this.aluno = aluno;
  }

  public EntidadeResumida getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(EntidadeResumida disciplina) {
    this.disciplina = disciplina;
  }
}
