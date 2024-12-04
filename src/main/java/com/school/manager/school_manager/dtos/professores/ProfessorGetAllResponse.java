package com.school.manager.school_manager.dtos.professores;

public class ProfessorGetAllResponse {
  private Long id;

  private String nome;

  private String email;

  private String disciplinaPrincipal;

  public ProfessorGetAllResponse(Long id, String nome, String email, String disciplinaPrincipal) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.disciplinaPrincipal = disciplinaPrincipal;
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

}
