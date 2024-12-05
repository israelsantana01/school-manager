package com.school.manager.school_manager.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Nota")
public class Nota {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "aluno_id", nullable = false)
  private Aluno aluno;

  @ManyToOne
  @JoinColumn(name = "disciplina_id", nullable = false)
  private Disciplina disciplina;

  private double valor;
  private LocalDate dataAvaliacao;

  public Nota(Long id, Aluno aluno, Disciplina disciplina, double valor, LocalDate dataAvaliacao) {
    this.id = id;
    this.aluno = aluno;
    this.disciplina = disciplina;
    this.valor = valor;
    this.dataAvaliacao = dataAvaliacao;
  }

  public Nota(Aluno aluno, Disciplina disciplina, double valor, LocalDate dataAvaliacao) {
    this.aluno = aluno;
    this.disciplina = disciplina;
    this.valor = valor;
    this.dataAvaliacao = dataAvaliacao;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
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

}

