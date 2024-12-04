package com.school.manager.school_manager.dtos.professores;

import java.util.List;

import com.school.manager.school_manager.helper.EntidadeResumida;

public class ProfessorGetByIdResponse {
  private Long id;

  private String nome;

  private String email;

  private String disciplinaPrincipal;
  
  private List<EntidadeResumida> disciplinas;

  public ProfessorGetByIdResponse(Long id, String nome, String email, String disciplinaPrincipal,
      List<EntidadeResumida> disciplinas) {
    this.id = id;
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

  public List<EntidadeResumida> getDisciplinas() {
    return disciplinas;
  }

  public void setDisciplinas(List<EntidadeResumida> disciplinas) {
    this.disciplinas = disciplinas;
  }
}
