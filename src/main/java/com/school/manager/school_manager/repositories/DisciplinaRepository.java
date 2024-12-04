package com.school.manager.school_manager.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.school.manager.school_manager.models.Disciplina;

@Repository
public class DisciplinaRepository {
  private SessionFactory sessionFactory;

  public DisciplinaRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public List<Disciplina> findAll() {
    Session session = sessionFactory.openSession();
    try {
      Query<Disciplina> query = session.createQuery(" FROM Disciplina", Disciplina.class);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar disciplinas: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Disciplina findById(Long id) {
    Session session = sessionFactory.openSession();
    try {
      Disciplina disciplina = session.get(Disciplina.class, id);

      if (disciplina != null) {
        return disciplina;
      }

      throw new Exception("Disciplina com ID " + id + " não encontrada.");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar disciplina: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Disciplina save(Disciplina disciplina) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.persist(disciplina);
      transaction.commit();
      return disciplina;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao salvar a disciplina: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Disciplina update(Disciplina disciplina) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.merge(disciplina);
      transaction.commit();
      return disciplina;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao atualizar a disciplina: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void delete(Long id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      Disciplina disciplina = session.get(Disciplina.class, id);

      if (disciplina != null) {
        session.remove(disciplina);
        transaction.commit();
      } else {
        throw new Exception("Disciplina com ID " + id + " não encontrada para exclusão.");
      }
    } catch (Exception e) {
      if (transaction != null) {
          transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao deletar disciplina: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

}

