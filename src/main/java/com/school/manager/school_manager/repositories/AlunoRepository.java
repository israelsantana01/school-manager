package com.school.manager.school_manager.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.school.manager.school_manager.models.Aluno;



@Repository
public class AlunoRepository {
  private SessionFactory sessionFactory;

  public AlunoRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public List<Aluno> findAll() {
    Session session = sessionFactory.openSession();
    try {
      Query<Aluno> query = session.createQuery(" FROM Aluno", Aluno.class);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar alunos: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Aluno findById(Long id) {
    Session session = sessionFactory.openSession();
    try {
      Aluno aluno = session.get(Aluno.class, id);

      if (aluno != null) {
        return aluno;
      }

      throw new Exception("Aluno com ID " + id + " não encontrado.");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar aluno: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void save(Aluno aluno) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.persist(aluno);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao salvar o aluno: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void update(Aluno aluno) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.merge(aluno);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao atualizar o aluno: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void delete(Long id) {
    Session session = sessionFactory.openSession(); // Abre uma nova sessão
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      Aluno aluno = session.get(Aluno.class, id);

      if (aluno != null) {
        session.remove(aluno);
        transaction.commit();
      } else {
        throw new Exception("Aluno com ID " + id + " não encontrado para exclusão.");
      }
    } catch (Exception e) {
      if (transaction != null) {
          transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao deletar aluno: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }


  // 1. Buscar alunos por nome contendo uma palavra-chave (case insensitive)
  // List<Aluno> findByNomeContainingIgnoreCase(String nome);

  // 2. Buscar alunos por email
  // Aluno findByEmail(String email);

  // 3. Buscar alunos por ano de nascimento
  // @Query("SELECT a FROM Aluno a WHERE YEAR(a.dataNascimento) = :ano")
  // List<Aluno> findByAnoNascimento(@Param("ano") int ano);

  // 4. Buscar alunos por turma (relacionamento com a entidade Turma)
  // @Query("SELECT a FROM Aluno a JOIN a.turmas t WHERE t.id = :turmaId")
  // List<Aluno> findByTurmaId(@Param("turmaId") Long turmaId);

  // 5. Contar alunos por turma
  // @Query("SELECT COUNT(a) FROM Aluno a JOIN a.turmas t WHERE t.id = :turmaId")
  // long countByTurmaId(@Param("turmaId") Long turmaId);

  // 6. Buscar alunos que possuem notas acima de um valor específico
  // @Query("SELECT DISTINCT a FROM Aluno a JOIN a.notas n WHERE n.valor > :notaMinima")
  // List<Aluno> findByNotasAcimaDe(@Param("notaMinima") double notaMinima);

  // 7. Buscar alunos sem notas cadastradas
  // @Query("SELECT a FROM Aluno a WHERE a.notas IS EMPTY")
  // List<Aluno> findAlunosSemNotas();

  // 8. Buscar alunos por intervalo de datas de nascimento
  // List<Aluno> findByDataNascimentoBetween(LocalDate inicio, LocalDate fim);
}

