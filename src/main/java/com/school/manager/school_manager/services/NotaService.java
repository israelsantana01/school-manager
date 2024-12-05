package com.school.manager.school_manager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.notas.NotaGetResponse;
import com.school.manager.school_manager.dtos.notas.NotaRequest;
import com.school.manager.school_manager.helper.EntidadeResumida;
import com.school.manager.school_manager.models.Aluno;
import com.school.manager.school_manager.models.Disciplina;
import com.school.manager.school_manager.models.Nota;
import com.school.manager.school_manager.repositories.AlunoRepository;
import com.school.manager.school_manager.repositories.DisciplinaRepository;
import com.school.manager.school_manager.repositories.NotaRepository;

@Service
public class NotaService {

  @Autowired
  private NotaRepository notaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Autowired
  private DisciplinaRepository disciplinaRepository;

  public List<NotaGetResponse> findAll() {
    try {
      List<Nota> itensBuscados = notaRepository.findAll();
      List<NotaGetResponse> retorno = new ArrayList<>();

      itensBuscados.forEach(item -> {
        retorno.add(new NotaGetResponse(
          item.getId(),
          item.getValor(),
          item.getDataAvaliacao(),
          new EntidadeResumida(item.getAluno().getId(), item.getAluno().getNome()),
          new EntidadeResumida(item.getDisciplina().getId(), item.getDisciplina().getNome())
        ));
      });

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public List<NotaGetResponse> findByAlunoId(Long alunoId) {
    try {
      List<Nota> itensBuscados = notaRepository.findByAlunoId(alunoId);
      List<NotaGetResponse> retorno = new ArrayList<>();

      itensBuscados.forEach(item -> {
        retorno.add(new NotaGetResponse(
          item.getId(),
          item.getValor(),
          item.getDataAvaliacao(),
          null,
          new EntidadeResumida(item.getDisciplina().getId(), item.getDisciplina().getNome())
        ));
      });

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public NotaGetResponse save(NotaRequest request) {
    try {
      Disciplina disciplina = this.disciplinaRepository.findById(request.getDisciplinaId());
      Aluno aluno = this.alunoRepository.findById(request.getAlunoId());

      Nota notaSalva = notaRepository.save(new Nota(
        aluno,
        disciplina,
        request.getValor(),
        request.getDataAvaliacao()
      ));

      return new NotaGetResponse(
        notaSalva.getId(),
        notaSalva.getValor(),
        notaSalva.getDataAvaliacao(),
        new EntidadeResumida(notaSalva.getAluno().getId(), notaSalva.getAluno().getNome()),
        new EntidadeResumida(notaSalva.getDisciplina().getId(), notaSalva.getDisciplina().getNome())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public NotaGetResponse update(NotaRequest request, Long id) {
    try {
      Nota notaJaSalva = this.notaRepository.findById(id);
      Disciplina disciplina = this.disciplinaRepository.findById(request.getDisciplinaId());
      Aluno aluno = this.alunoRepository.findById(request.getAlunoId());

      Nota notaAtualizada = notaRepository.update(new Nota(
        notaJaSalva.getId(),
        aluno,
        disciplina,
        request.getValor(),
        request.getDataAvaliacao()
      ));

      return new NotaGetResponse(
        notaAtualizada.getId(),
        notaAtualizada.getValor(),
        notaAtualizada.getDataAvaliacao(),
        new EntidadeResumida(notaAtualizada.getAluno().getId(), notaAtualizada.getAluno().getNome()),
        new EntidadeResumida(notaAtualizada.getDisciplina().getId(), notaAtualizada.getDisciplina().getNome())
      );
    } catch (Exception e) {
      throw e;
    }
  }

  public void deleteById(Long id) {
    try {
      notaRepository.delete(id);
    } catch (Exception e) {
      throw e;
    }
  }

}
