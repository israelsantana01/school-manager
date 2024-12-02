package com.school.manager.school_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.models.Aluno;
import com.school.manager.school_manager.repositories.AlunoRepository;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository alunoRepository;

  // Listar todos os alunos
  public List<Aluno> findAll() {
    try {
      // List<Aluno> alunos = alunoRepository.findAll();
      // alunos.forEach(aluno -> {
      //   if (aluno.getTurmas() == null) {
      //     aluno.setTurmas(new ArrayList<>());
      //   }
      // });
      // return alunos;
      return  alunoRepository.findAll();
    } catch (Exception e) {
      throw e;
    }
  }

  // Buscar aluno por ID
  public Aluno findById(Long id) {
    try {
      return alunoRepository.findById(id);
    } catch (Exception e) {
      throw e;
    }
  }

  // Criar ou atualizar um aluno
  public void save(Aluno aluno) {
    try {
      alunoRepository.save(aluno);
    } catch (Exception e) {
      throw e;
    }
  }

  public void update(Aluno aluno) {
    try {
      alunoRepository.update(aluno);
    } catch (Exception e) {
      throw e;
    }
  }

  // // Deletar aluno por ID
  public void deleteById(Long id) {
    try {
      alunoRepository.delete(id);
    } catch (Exception e) {
      throw e;
    }
  }
}
