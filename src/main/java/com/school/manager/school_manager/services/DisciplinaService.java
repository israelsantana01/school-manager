package com.school.manager.school_manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.DisciplinaRequest;
import com.school.manager.school_manager.models.Disciplina;
import com.school.manager.school_manager.models.Professor;
import com.school.manager.school_manager.models.Turma;
import com.school.manager.school_manager.repositories.DisciplinaRepository;
import com.school.manager.school_manager.repositories.ProfessorRepository;
import com.school.manager.school_manager.repositories.TurmaRepository;

@Service
public class DisciplinaService {

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @Autowired
  private ProfessorRepository professorRepository;

  @Autowired
  private TurmaRepository turmaRepository;

  public List<Disciplina> findAll() {
    try {
      return disciplinaRepository.findAll();
    } catch (Exception e) {
      throw e;
    }
  }

  public Disciplina findById(Long id) {
    try {
      return disciplinaRepository.findById(id);
    } catch (Exception e) {
      throw e;
    }
  }

  public Disciplina save(DisciplinaRequest request) {
    try {
      Disciplina disciplina = new Disciplina();

      Professor professor = professorRepository.findById(request.getProfessorId());
      if (professor.getId() == null) {
        throw new RuntimeException("Professor não encontrado com ID: " + request.getProfessorId());
      }

      disciplina.setNome(request.getNome());
      disciplina.setCargaHoraria(request.getCargaHoraria());
      disciplina.setProfessor(professor);

      if (request.getTurmas() != null && !request.getTurmas().isEmpty()) {
        List<Turma> turmas = turmaRepository.findAllById(request.getTurmas());
        disciplina.setTurmas(turmas);
      }

      return disciplinaRepository.save(disciplina);
    } catch (Exception e) {
      throw e;
    }
  }

  public Disciplina update(DisciplinaRequest request, Long id) {
    try {
      Disciplina disciplina = new Disciplina();
      disciplina.setId(id);

      Professor professor = professorRepository.findById(request.getProfessorId());
      if (professor.getId() == null) {
        throw new RuntimeException("Professor não encontrado com ID: " + request.getProfessorId());
      }

      disciplina.setNome(request.getNome());
      disciplina.setCargaHoraria(request.getCargaHoraria());
      disciplina.setProfessor(professor);

      if (request.getTurmas() != null && !request.getTurmas().isEmpty()) {
        List<Turma> turmas = turmaRepository.findAllById(request.getTurmas());
        disciplina.setTurmas(turmas);
      }

      return disciplinaRepository.update(disciplina);
    } catch (Exception e) {
      throw e;
    }
  }

  public void deleteById(Long id) {
    try {
      disciplinaRepository.delete(id);
    } catch (Exception e) {
      throw e;
    }
  }

}
