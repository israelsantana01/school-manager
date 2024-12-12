package com.school.manager.school_manager.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Disciplina")
public class Disciplina {
  public Disciplina(Long id, String nome, int cargaHoraria, Professor professor, List<Turma> turmas) {
    this.id = id;
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
    this.professor = professor;
    this.turmas = turmas;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private int cargaHoraria;

  @ManyToOne
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "disciplina_turma",
    joinColumns = @JoinColumn(name = "disciplina_id"),
    inverseJoinColumns = @JoinColumn(name = "turma_id")
  )
  private List<Turma> turmas = new ArrayList<>();

  public Disciplina(String nome, int cargaHoraria, Professor professor, List<Turma> turmas) {
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
    this.professor = professor;
    this.turmas = turmas;
  }

  public Disciplina() {
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

  public int getCargaHoraria() {
    return cargaHoraria;
  }

  public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
  }

  public Professor getProfessor() {
    return professor;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }

  public List<Turma> getTurmas() {
    return turmas;
  }

  public void setTurmas(List<Turma> turmas) {
    this.turmas = turmas;
  }
}
