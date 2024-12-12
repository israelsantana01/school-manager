package com.school.manager.school_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.turmas.TurmaGetAllResponse;
import com.school.manager.school_manager.dtos.turmas.TurmaGetByIdResponse;
import com.school.manager.school_manager.dtos.turmas.TurmaRequest;
import com.school.manager.school_manager.helper.EntidadeResumida;
import com.school.manager.school_manager.models.Aluno;
import com.school.manager.school_manager.models.Disciplina;
import com.school.manager.school_manager.models.Professor;
import com.school.manager.school_manager.models.Turma;
import com.school.manager.school_manager.repositories.AlunoRepository;
import com.school.manager.school_manager.repositories.DisciplinaRepository;
import com.school.manager.school_manager.repositories.ProfessorRepository;
import com.school.manager.school_manager.repositories.TurmaRepository;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository turmaRepository;

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  @Autowired
  private ProfessorRepository professorRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  public List<TurmaGetAllResponse> findAll() {
    try {
      List<Turma> itensBuscados = turmaRepository.findAll();

      List<TurmaGetAllResponse> retorno = new ArrayList<>();
      itensBuscados.forEach(item -> {
        retorno.add(new TurmaGetAllResponse(item.getId(), item.getNome(), item.getAno()));
      });

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public TurmaGetByIdResponse findById(Long id) {
    try {
      Turma itemBuscado = turmaRepository.findById(id);

      TurmaGetByIdResponse retorno = new TurmaGetByIdResponse(
        itemBuscado.getId(),
        itemBuscado.getNome(),
        itemBuscado.getAno(),
        itemBuscado.getAlunos().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList()),
        itemBuscado.getDisciplinas().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList()),
        itemBuscado.getProfessores().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList())
      );

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public TurmaGetByIdResponse save(TurmaRequest request) {
    try {
      List<Aluno> alunos = new ArrayList<>();
      if (request.getAlunosIds() != null && !request.getAlunosIds().isEmpty()) {
        alunos = alunoRepository.findAllById(request.getAlunosIds());
      }

      List<Professor> professores = new ArrayList<>();
      if (request.getProfessoresIds() != null && !request.getProfessoresIds().isEmpty()) {
        professores = professorRepository.findAllById(request.getProfessoresIds());
      }

      List<Disciplina> disciplinas = new ArrayList<>();
      if (request.getDisciplinasIds() != null && !request.getDisciplinasIds().isEmpty()) {
        disciplinas = disciplinaRepository.findAllById(request.getDisciplinasIds());
      }

      Turma novaTurma = new Turma(
        request.getNome(),
        request.getAno(),
        alunos,
        professores,
        disciplinas
      );

      Turma itemSalvo = turmaRepository.save(novaTurma);

      return new TurmaGetByIdResponse(
        itemSalvo.getId(),
        itemSalvo.getNome(),
        itemSalvo.getAno(),
        itemSalvo.getAlunos().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList()),
        itemSalvo.getDisciplinas().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList()),
        itemSalvo.getProfessores().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public TurmaGetByIdResponse update(TurmaRequest request, Long id) {
    try {
      Turma turmaSalva = turmaRepository.findById(id);

      List<Aluno> alunos = new ArrayList<>();
      if (request.getAlunosIds() != null && !request.getAlunosIds().isEmpty()) {
        alunos = alunoRepository.findAllById(request.getAlunosIds());
      }

      List<Professor> professores = new ArrayList<>();
      if (request.getProfessoresIds() != null && !request.getProfessoresIds().isEmpty()) {
        professores = professorRepository.findAllById(request.getProfessoresIds());
      }

      for (Professor professor : professores.isEmpty() ? turmaSalva.getProfessores() : professores) {
        if (professores.isEmpty()) {
          professor.getTurmas().remove(turmaSalva);
        } else {
          professor.getTurmas().add(turmaSalva);
        }

        professorRepository.update(professor);
      }

      List<Disciplina> disciplinas = new ArrayList<>();
      if (request.getDisciplinasIds() != null && !request.getDisciplinasIds().isEmpty()) {
        disciplinas = disciplinaRepository.findAllById(request.getDisciplinasIds());
      }

      Turma turmaAtualizada = new Turma(
        turmaSalva.getId(),
        request.getNome(),
        request.getAno(),
        alunos,
        professores,
        disciplinas
      );

      Turma itemAtualizado = turmaRepository.update(turmaAtualizada);

      return new TurmaGetByIdResponse(
        itemAtualizado.getId(),
        itemAtualizado.getNome(),
        itemAtualizado.getAno(),
        itemAtualizado.getAlunos().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList()),
        itemAtualizado.getDisciplinas().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList()),
        itemAtualizado.getProfessores().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public void deleteById(Long id) {
    try {
      turmaRepository.delete(id);
    } catch (Exception e) {
      throw e;
    }
  }

}
