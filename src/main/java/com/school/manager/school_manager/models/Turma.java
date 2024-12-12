package com.school.manager.school_manager.models;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private int ano;

  @OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
  private List<Aluno> alunos = new ArrayList<>();

  @ManyToMany(mappedBy = "turmas", fetch = FetchType.EAGER)
  private List<Professor> professores = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "disciplina_turma",
    joinColumns = @JoinColumn(name = "turma_id"),
    inverseJoinColumns = @JoinColumn(name = "disciplina_id")
  )
  private List<Disciplina> disciplinas = new ArrayList<>();

  public Turma(Long id, String nome, int ano, List<Aluno> alunos, List<Professor> professores,
      List<Disciplina> disciplinas) {
    this.id = id;
    this.nome = nome;
    this.ano = ano;
    this.alunos = alunos;
    this.professores = professores;
    this.disciplinas = disciplinas;
  }

  public Turma(String nome, int ano, List<Aluno> alunos, List<Professor> professores, List<Disciplina> disciplinas) {
    this.nome = nome;
    this.ano = ano;
    this.alunos = alunos;
    this.professores = professores;
    this.disciplinas = disciplinas;
  }

  public Turma() {
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

  public List<Aluno> getAlunos() {
    return alunos;
  }

  public void setAlunos(List<Aluno> alunos) {
    this.alunos = alunos;
  }

  public List<Disciplina> getDisciplinas() {
    return disciplinas;
  }

  public void setDisciplinas(List<Disciplina> disciplinas) {
    this.disciplinas = disciplinas;
  }

  public List<Professor> getProfessores() {
    return professores;
  }

  public void setProfessores(List<Professor> professores) {
    this.professores = professores;
  }

}
