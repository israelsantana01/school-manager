package com.school.manager.school_manager.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.manager.school_manager.views.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Professor")
public class Professor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true, nullable = false)
  private String email;

  private String disciplinaPrincipal;

  @JsonView(Views.Internal.class)
  @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
  private List<Disciplina> disciplinas;

  @ManyToMany
  @JoinTable(
    name = "professor_turma",
    joinColumns = @JoinColumn(name = "professor_id"),
    inverseJoinColumns = @JoinColumn(name = "turma_id")
  )
  private List<Turma> turmas;

  public Professor() { }

  public Professor(Long id, String nome, String email, String disciplinaPrincipal, List<Disciplina> disciplinas) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.disciplinaPrincipal = disciplinaPrincipal;
    this.disciplinas = disciplinas;
  }

  public Professor(String nome, String email, String disciplinaPrincipal, List<Disciplina> disciplinas) {
    this.nome = nome;
    this.email = email;
    this.disciplinaPrincipal = disciplinaPrincipal;
    this.disciplinas = disciplinas;
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

  public List<Disciplina> getDisciplinas() {
    return disciplinas;
  }

  public void setDisciplinas(List<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }
}

