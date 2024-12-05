package com.school.manager.school_manager.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.school.manager.school_manager.models.Turma;

@Repository
public class TurmaRepository {
  private SessionFactory sessionFactory;

  public TurmaRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public List<Turma> findAll() {
    Session session = sessionFactory.openSession();
    try {
      Query<Turma> query = session.createQuery(" FROM Turma", Turma.class);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar turmas: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public List<Turma> findAllById(List<Long> ids) {
    Session session = sessionFactory.openSession();

    try {
      Query<Turma> query = session.createQuery("FROM Turma WHERE id IN :ids", Turma.class);
      query.setParameter("ids", ids);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar turmas: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public List<Turma> findAllByDisciplinaId(Long disciplinaId) {
    Session session = sessionFactory.openSession();

    try {
      Query<Turma> query = session.createQuery("FROM Turma t JOIN t.disciplinas d WHERE d.id = :disciplinaId", Turma.class);
      query.setParameter("disciplinaId", disciplinaId);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar turmas: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Turma findById(Long id) {
    Session session = sessionFactory.openSession();
    try {
      Turma turma = session.get(Turma.class, id);

      if (turma != null) {
        return turma;
      }

      throw new Exception("Turma com ID " + id + " não encontrada.");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar turma: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Turma save(Turma turma) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.persist(turma);
      transaction.commit();
      return turma;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao salvar a turma: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Turma update(Turma turma) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.merge(turma);
      transaction.commit();
      return turma;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao atualizar a turma: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void delete(Long id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      Turma turma = session.get(Turma.class, id);

      if (turma != null) {
        session.remove(turma);
        transaction.commit();
      } else {
        throw new Exception("Turma com ID " + id + " não encontrada para exclusão.");
      }
    } catch (Exception e) {
      if (transaction != null) {
          transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao deletar turma: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

}

