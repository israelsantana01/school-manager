package com.school.manager.school_manager.dtos;

import java.util.List;

public class DisciplinaRequest {
  private String nome;
  private int cargaHoraria;
  private Long professorId;
  private List<Long> turmasIds;

  public List<Long> getTurmasIds() {
    return turmasIds;
  }

  public void setTurmasIds(List<Long> turmasIds) {
    this.turmasIds = turmasIds;
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

  public Long getProfessorId() {
    return professorId;
  }

  public void setProfessorId(Long professorId) {
    this.professorId = professorId;
  }

}
