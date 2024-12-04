package com.school.manager.school_manager.dtos.disciplinas;

import java.util.List;

import com.school.manager.school_manager.helper.EntidadeResumida;

public class DisciplinaGetByIdResponse {
  private Long id;

  private String nome;

  private int cargaHoraria;

  private EntidadeResumida professor;

  private List<EntidadeResumida> turmas;

  public DisciplinaGetByIdResponse(Long id, String nome, int cargaHoraria, EntidadeResumida professor,
      List<EntidadeResumida> turmas) {
    this.id = id;
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
    this.professor = professor;
    this.turmas = turmas;
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

  public List<EntidadeResumida> getTurmas() {
    return turmas;
  }

  public void setTurmas(List<EntidadeResumida> turmas) {
    this.turmas = turmas;
  }

  
}
