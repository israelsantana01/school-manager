package com.school.manager.school_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.DisciplinaRequest;
import com.school.manager.school_manager.dtos.disciplinas.DisciplinaGetAllResponse;
import com.school.manager.school_manager.dtos.disciplinas.DisciplinaGetByIdResponse;
import com.school.manager.school_manager.helper.EntidadeResumida;
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

  public List<DisciplinaGetAllResponse> findAll() {
    try {
      List<Disciplina> itensBuscados = disciplinaRepository.findAll();
      List<DisciplinaGetAllResponse> retorno = new ArrayList<>();

      itensBuscados.forEach(item -> {
        retorno.add(new DisciplinaGetAllResponse(
          item.getId(),
          item.getNome(),
          item.getCargaHoraria(),
          new EntidadeResumida(item.getProfessor().getId(), item.getProfessor().getNome())
        ));
      });

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public DisciplinaGetByIdResponse findById(Long id) {
    try {
      Disciplina itemBuscado = disciplinaRepository.findById(id);
      return new DisciplinaGetByIdResponse(
        itemBuscado.getId(),
        itemBuscado.getNome(),
        itemBuscado.getCargaHoraria(),
        new EntidadeResumida(itemBuscado.getProfessor().getId(), itemBuscado.getProfessor().getNome()),
        itemBuscado.getTurmas().stream().map(turma -> new EntidadeResumida(
          turma.getId(),
          turma.getNome()
        )).collect(Collectors.toList())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public DisciplinaGetByIdResponse save(DisciplinaRequest request) {
    try {
      Professor professor = professorRepository.findById(request.getProfessorId());

      List<Turma> turmas = new ArrayList<>();
      if (request.getTurmasIds() != null && !request.getTurmasIds().isEmpty()) {
        turmas = turmaRepository.findAllById(request.getTurmasIds());
      }

      Disciplina itemSalvo = disciplinaRepository.save(new Disciplina(
        request.getNome(),
        request.getCargaHoraria(),
        professor,
        turmas
      ));

      return new DisciplinaGetByIdResponse(
        itemSalvo.getId(),
        itemSalvo.getNome(),
        itemSalvo.getCargaHoraria(),
        new EntidadeResumida(itemSalvo.getProfessor().getId(), itemSalvo.getProfessor().getNome()),
        itemSalvo.getTurmas().stream().map(turma -> new EntidadeResumida(
          turma.getId(),
          turma.getNome()
        )).collect(Collectors.toList())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public DisciplinaGetByIdResponse update(DisciplinaRequest request, Long id) {
    try {
      Disciplina disciplina = disciplinaRepository.findById(id);
      Professor professor = professorRepository.findById(request.getProfessorId());

      List<Turma> turmas = new ArrayList<>();
      if (request.getTurmasIds() != null && !request.getTurmasIds().isEmpty()) {
        turmas = turmaRepository.findAllById(request.getTurmasIds());
      }

      Disciplina itemSalvo = disciplinaRepository.update(new Disciplina(
        disciplina.getId(),
        request.getNome(),
        request.getCargaHoraria(),
        professor,
        turmas
      ));

      return new DisciplinaGetByIdResponse(
        itemSalvo.getId(),
        itemSalvo.getNome(),
        itemSalvo.getCargaHoraria(),
        new EntidadeResumida(itemSalvo.getProfessor().getId(), itemSalvo.getProfessor().getNome()),
        itemSalvo.getTurmas().stream().map(turma -> new EntidadeResumida(
          turma.getId(),
          turma.getNome()
        )).collect(Collectors.toList())
      );
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
