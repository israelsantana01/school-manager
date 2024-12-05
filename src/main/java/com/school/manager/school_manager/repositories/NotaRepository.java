package com.school.manager.school_manager.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.school.manager.school_manager.models.Nota;

@Repository
public class NotaRepository {
  private SessionFactory sessionFactory;

  public NotaRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public List<Nota> findAll() {
    Session session = sessionFactory.openSession();
    try {
      Query<Nota> query = session.createQuery(" FROM Nota", Nota.class);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar notas: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public List<Nota> findByAlunoId(Long alunoId) {
    Session session = sessionFactory.openSession();

    try {
      Query<Nota> query = session.createQuery("FROM Nota WHERE id = :id", Nota.class);
      query.setParameter("id", alunoId);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar notas: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Nota findById(Long id) {
    Session session = sessionFactory.openSession();

    try {
      Nota nota = session.get(Nota.class, id);

      if (nota != null) {
        return nota;
      }

      throw new Exception("Nota com ID " + id + " não encontrada.");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar nota: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Nota save(Nota nota) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.persist(nota);
      transaction.commit();
      return nota;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao salvar a nota: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Nota update(Nota nota) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.merge(nota);
      transaction.commit();
      return nota;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao atualizar a nota: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void delete(Long id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      Nota nota = session.get(Nota.class, id);

      if (nota != null) {
        session.remove(nota);
        transaction.commit();
      } else {
        throw new Exception("Nota com ID " + id + " não encontrada para exclusão.");
      }
    } catch (Exception e) {
      if (transaction != null) {
          transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao deletar nota: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }
}
