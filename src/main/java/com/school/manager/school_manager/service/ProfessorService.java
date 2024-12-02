package com.school.manager.school_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.models.Professor;
import com.school.manager.school_manager.repositories.ProfessorRepository;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository professorRepository;

  public List<Professor> findAll() {
    try {
      return professorRepository.findAll();
    } catch (Exception e) {
      throw e;
    }
  }

  public Professor findById(Long id) {
    try {
      return professorRepository.findById(id);
    } catch (Exception e) {
      throw e;
    }
  }

  public void save(Professor professor) {
    try {
      professorRepository.save(professor);
    } catch (Exception e) {
      throw e;
    }
  }

  public void update(Professor professor) {
    try {
      professorRepository.update(professor);
    } catch (Exception e) {
      throw e;
    }
  }

  public void deleteById(Long id) {
    try {
      professorRepository.delete(id);
    } catch (Exception e) {
      throw e;
    }
  }
}
