package com.school.manager.school_manager.dtos.turmas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.manager.school_manager.helper.EntidadeResumida;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TurmaGetByIdResponse {
  private Long id;

  private String nome;
  
  private int ano;

  private List<EntidadeResumida> alunos;

  private List<EntidadeResumida> disciplinas;
  
  private List<EntidadeResumida> professores;

  public TurmaGetByIdResponse(Long id, String nome, int ano, List<EntidadeResumida> alunos,
      List<EntidadeResumida> disciplinas, List<EntidadeResumida> professores) {
    this.id = id;
    this.nome = nome;
    this.ano = ano;
    this.alunos = alunos;
    this.disciplinas = disciplinas;
    this.professores = professores;
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

  public List<EntidadeResumida> getAlunos() {
    return alunos;
  }

  public void setAlunos(List<EntidadeResumida> alunos) {
    this.alunos = alunos;
  }

  public List<EntidadeResumida> getDisciplinas() {
    return disciplinas;
  }

  public void setDisciplinas(List<EntidadeResumida> disciplinas) {
    this.disciplinas = disciplinas;
  }

  public List<EntidadeResumida> getProfessores() {
    return professores;
  }

  public void setProfessores(List<EntidadeResumida> professores) {
    this.professores = professores;
  }

}
