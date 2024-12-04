package com.school.manager.school_manager.dtos.disciplinas;

import com.school.manager.school_manager.helper.EntidadeResumida;

public class DisciplinaGetAllResponse {
  private Long id;

  private String nome;

  private int cargaHoraria;

  private EntidadeResumida professor;

  public DisciplinaGetAllResponse(Long id, String nome, int cargaHoraria, EntidadeResumida professor) {
    this.id = id;
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
    this.professor = professor;
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

  public EntidadeResumida getProfessor() {
    return professor;
  }

  public void setProfessor(EntidadeResumida professor) {
    this.professor = professor;
  }

  
}
