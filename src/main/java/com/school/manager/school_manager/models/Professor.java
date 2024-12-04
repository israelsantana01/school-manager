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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Professor")
public class Professor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(Views.Public.class)
  private Long id;

  @JsonView(Views.Public.class)
  private String nome;

  @JsonView(Views.Public.class)
  @Column(unique = true, nullable = false)
  private String email;

  @JsonView(Views.Internal.class)
  private String disciplinaPrincipal;

  @JsonView(Views.Internal.class)
  @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
  private List<Disciplina> disciplinas;

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

