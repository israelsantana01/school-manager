package com.school.manager.school_manager.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.manager.school_manager.views.Views;

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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(Views.Public.class)
  private Long id;

  @JsonView(Views.Public.class)
  private String nome;

  @JsonView(Views.Public.class)
  private int cargaHoraria;

  @JsonView(Views.Internal.class)
  @ManyToOne
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;

  @JsonView(Views.Internal.class)
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "disciplina_turma",
    joinColumns = @JoinColumn(name = "disciplina_id"),
    inverseJoinColumns = @JoinColumn(name = "turma_id")
  )
  private List<Turma> turmas;

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
