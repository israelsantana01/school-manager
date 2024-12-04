package com.school.manager.school_manager.models;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "Turma")
public class Turma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @JsonView(Views.Public.class)
  private Long id;

  // @JsonView(Views.Public.class)
  private String nome;

  // @JsonView(Views.Public.class)
  private int ano;

  // @JsonView(Views.Internal.class)
  @OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
  private List<Aluno> alunos = new ArrayList<>();

  @ManyToMany(mappedBy = "turmas")
  private List<Professor> professores;

  // @JsonView(Views.Internal.class)
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "disciplina_turma",
    joinColumns = @JoinColumn(name = "turma_id"),
    inverseJoinColumns = @JoinColumn(name = "disciplina_id")
  )
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

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public List<Aluno> getAlunos() {
    return alunos;
  }

  // public List<EntidadeResumida> getAlunosResumido() {
  //   List<EntidadeResumida> retorno = new ArrayList<>();

  //   alunos.forEach(item -> { retorno.add(new EntidadeResumida(item.getId(), item.getNome())); });
  //   return retorno;
  // }

  public void setAlunos(List<Aluno> alunos) {
    this.alunos = alunos;
  }

  public List<Disciplina> getDisciplinas() {
    return disciplinas;
  }

  // public List<EntidadeResumida> getDisciplinasResumido() {
  //   List<EntidadeResumida> retorno = new ArrayList<>();

  //   disciplinas.forEach(item -> { retorno.add(new EntidadeResumida(item.getId(), item.getNome())); });
  //   return retorno;
  // }

  public void setDisciplinas(List<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }

}
