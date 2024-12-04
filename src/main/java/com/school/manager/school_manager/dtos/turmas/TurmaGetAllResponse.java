package com.school.manager.school_manager.dtos.turmas;

public class TurmaGetAllResponse {
  private Long id;
  private String nome;
  private int ano;

  public TurmaGetAllResponse(Long id, String nome, int ano) {
    this.id = id;
    this.nome = nome;
    this.ano = ano;
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

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

}
