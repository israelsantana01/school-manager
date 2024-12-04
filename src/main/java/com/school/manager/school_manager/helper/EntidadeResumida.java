package com.school.manager.school_manager.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EntidadeResumida {
  Long id;
  String nome;
  int cargaHoraria;

  public EntidadeResumida(Long id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public EntidadeResumida(Long id, String nome, int cargaHoraria) {
    this.id = id;
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
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

}
