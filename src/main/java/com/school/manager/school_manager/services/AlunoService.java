package com.school.manager.school_manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.models.Aluno;
import com.school.manager.school_manager.repositories.AlunoRepository;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository alunoRepository;

  public List<Aluno> findAll() {
    try {
      return  alunoRepository.findAll();
    } catch (Exception e) {
      throw e;
    }
  }

  public Aluno findById(Long id) {
    try {
      return alunoRepository.findById(id);
    } catch (Exception e) {
      throw e;
    }
  }

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

  public void deleteById(Long id) {
    try {
      alunoRepository.delete(id);
    } catch (Exception e) {
      throw e;
    }
  }
}
