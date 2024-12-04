package com.school.manager.school_manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.manager.school_manager.dtos.turmas.TurmaGetAllResponse;
import com.school.manager.school_manager.dtos.turmas.TurmaGetByIdResponse;
import com.school.manager.school_manager.helper.EntidadeResumida;
import com.school.manager.school_manager.models.Turma;
import com.school.manager.school_manager.repositories.TurmaRepository;

@Service
public class TurmaService {

  @Autowired
  private TurmaRepository turmaRepository;

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
        itemBuscado.getDisciplinas().stream().map(item -> new EntidadeResumida(item.getId(), item.getNome())).collect(Collectors.toList())
      );

      return retorno;
    } catch (Exception e) {
      throw e;
    }
  }

  public Turma save(Turma turma) {
    try {
      return turmaRepository.save(turma);
    } catch (Exception e) {
      throw e;
    }
  }

  public Turma update(Turma turma) {
    try {
      return turmaRepository.update(turma);
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
