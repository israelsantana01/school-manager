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

  public List<Aluno> findAllById(List<Long> ids) {
    Session session = sessionFactory.openSession();

    try {
      Query<Aluno> query = session.createQuery("FROM Aluno WHERE id IN :ids", Aluno.class);
      query.setParameter("ids", ids);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar turmas: " + e.getMessage(), e);
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

  public Aluno save(Aluno aluno) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.persist(aluno);
      transaction.commit();
      return aluno;
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

  public Aluno update(Aluno aluno) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.merge(aluno);
      transaction.commit();
      return aluno;
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
    Session session = sessionFactory.openSession();
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
}
