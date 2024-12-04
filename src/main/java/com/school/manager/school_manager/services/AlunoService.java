package com.school.manager.school_manager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.alunos.AlunoGetResponse;
import com.school.manager.school_manager.dtos.alunos.AlunoRequest;
import com.school.manager.school_manager.helper.EntidadeResumida;
import com.school.manager.school_manager.models.Aluno;
import com.school.manager.school_manager.models.Turma;
import com.school.manager.school_manager.repositories.AlunoRepository;
import com.school.manager.school_manager.repositories.TurmaRepository;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository alunoRepository;

  @Autowired
  private TurmaRepository turmaRepository;

  public List<AlunoGetResponse> findAll() {
    try {
      List<Aluno> itensBuscados = alunoRepository.findAll();
      List<AlunoGetResponse> retorno = new ArrayList<>();

      itensBuscados.forEach(item -> {
        retorno.add(new AlunoGetResponse(
          item.getId(),
          item.getNome(),
          item.getMatricula(),
          item.getEmail(),
          item.getDataNascimento(),
          new EntidadeResumida(item.getTurma().getId(), item.getTurma().getNome())
        ));
      });

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public AlunoGetResponse findById(Long id) {
    try {
      Aluno itemBuscado = alunoRepository.findById(id);
      return new AlunoGetResponse(
        itemBuscado.getId(),
        itemBuscado.getNome(),
        itemBuscado.getMatricula(),
        itemBuscado.getEmail(),
        itemBuscado.getDataNascimento(),
        new EntidadeResumida(itemBuscado.getTurma().getId(), itemBuscado.getTurma().getNome())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public AlunoGetResponse save(AlunoRequest request) {
    try {
      Turma turma = this.turmaRepository.findById(request.getTurmaId());

      Aluno alunoSalvo = alunoRepository.save(new Aluno(
        request.getNome(),
        gerarMatriculaComTimestamp(),
        request.getEmail(),
        request.getDataNascimento(),
        turma
      ));

      return new AlunoGetResponse(
        alunoSalvo.getId(),
        alunoSalvo.getNome(),
        alunoSalvo.getMatricula(),
        alunoSalvo.getEmail(),
        alunoSalvo.getDataNascimento(),
        new EntidadeResumida(alunoSalvo.getTurma().getId(), alunoSalvo.getTurma().getNome())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public AlunoGetResponse update(AlunoRequest request, Long id) {
    try {
      Aluno alunoJaSalvo = this.alunoRepository.findById(id);
      Turma turma = this.turmaRepository.findById(request.getTurmaId());

      Aluno alunoAtualizado = alunoRepository.update(new Aluno(
        alunoJaSalvo.getId(),
        alunoJaSalvo.getMatricula(),
        request.getNome(),
        request.getEmail(),
        request.getDataNascimento(),
        turma
      ));

      return new AlunoGetResponse(
        alunoAtualizado.getId(),
        alunoAtualizado.getNome(),
        alunoAtualizado.getMatricula(),
        alunoAtualizado.getEmail(),
        alunoAtualizado.getDataNascimento(),
        new EntidadeResumida(alunoAtualizado.getTurma().getId(), alunoAtualizado.getTurma().getNome())
      );
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

  private String gerarMatriculaComTimestamp() {
    long timestamp = System.currentTimeMillis();
    int randomSuffix = (int) (Math.random() * 1000);
    return timestamp + String.format("%03d", randomSuffix);
  }
}
