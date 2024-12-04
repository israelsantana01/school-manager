package com.school.manager.school_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.professores.ProfessorGetAllResponse;
import com.school.manager.school_manager.dtos.professores.ProfessorGetByIdResponse;
import com.school.manager.school_manager.dtos.professores.ProfessorRequest;
import com.school.manager.school_manager.helper.EntidadeResumida;
import com.school.manager.school_manager.models.Professor;
import com.school.manager.school_manager.repositories.DisciplinaRepository;
import com.school.manager.school_manager.repositories.ProfessorRepository;

@Service
public class ProfessorService {

  @Autowired
  private ProfessorRepository professorRepository;

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  public List<ProfessorGetAllResponse> findAll() {
    try {
      List<Professor> itensBuscados = professorRepository.findAll();
      List<ProfessorGetAllResponse> retorno = new ArrayList<>();

      itensBuscados.forEach(item -> {
        retorno.add(new ProfessorGetAllResponse(
          item.getId(),
          item.getNome(),
          item.getEmail(),
          item.getDisciplinaPrincipal()
        ));
      });

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public ProfessorGetByIdResponse findById(Long id) {
    try {
      Professor itemBuscado = professorRepository.findById(id);
      return new ProfessorGetByIdResponse(
        itemBuscado.getId(),
        itemBuscado.getNome(),
        itemBuscado.getEmail(),
        itemBuscado.getDisciplinaPrincipal(),
        itemBuscado.getDisciplinas().stream().map(disciplina -> new EntidadeResumida(
          disciplina.getId(),
          disciplina.getNome(),
          disciplina.getCargaHoraria()
        )).collect(Collectors.toList())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public ProfessorGetByIdResponse save(ProfessorRequest request) {
    try {
      Professor itemSalvo = professorRepository.save(new Professor(
        request.getNome(),
        request.getEmail(),
        request.getDisciplinaPrincipal(),
        new ArrayList<>()
      ));

      return new ProfessorGetByIdResponse(
        itemSalvo.getId(),
        itemSalvo.getNome(),
        itemSalvo.getEmail(),
        itemSalvo.getDisciplinaPrincipal(),
        itemSalvo.getDisciplinas().stream().map(disciplina -> new EntidadeResumida(
          disciplina.getId(),
          disciplina.getNome(),
          disciplina.getCargaHoraria()
        )).collect(Collectors.toList())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public ProfessorGetByIdResponse update(ProfessorRequest request, Long id) {
    try {
      Professor professorSalvo = this.professorRepository.findById(id);

      Professor itemAtualizado = professorRepository.update(new Professor(
        professorSalvo.getId(),
        request.getNome(),
        request.getEmail(),
        request.getDisciplinaPrincipal(),
        new ArrayList<>()
      ));

      return new ProfessorGetByIdResponse(
        itemAtualizado.getId(),
        itemAtualizado.getNome(),
        itemAtualizado.getEmail(),
        itemAtualizado.getDisciplinaPrincipal(),
        itemAtualizado.getDisciplinas().stream().map(disciplina -> new EntidadeResumida(
          disciplina.getId(),
          disciplina.getNome(),
          disciplina.getCargaHoraria()
        )).collect(Collectors.toList())
      );
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
