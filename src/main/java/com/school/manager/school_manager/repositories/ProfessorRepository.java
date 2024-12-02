package com.school.manager.school_manager.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.school.manager.school_manager.models.Professor;



@Repository
public class ProfessorRepository {
  private SessionFactory sessionFactory;

  public ProfessorRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public List<Professor> findAll() {
    Session session = sessionFactory.openSession();
    try {
      Query<Professor> query = session.createQuery(" FROM Professor", Professor.class);
      return query.getResultList();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar professores: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public Professor findById(Long id) {
    Session session = sessionFactory.openSession();
    try {
      Professor professor = session.get(Professor.class, id);

      if (professor != null) {
        return professor;
      }

      throw new Exception("Professor com ID " + id + " não encontrado.");
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar professor: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void save(Professor professor) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.persist(professor);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao salvar o professor: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void update(Professor professor) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.merge(professor);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new RuntimeException("Erro ao atualizar o professor: " + e.getMessage(), e);
    } finally {
      session.close();
    }
  }

  public void delete(Long id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      Professor professor = session.get(Professor.class, id);

      if (professor != null) {
        session.remove(professor);
        transaction.commit();
      } else {
        throw new Exception("Professor com ID " + id + " não encontrado para exclusão.");
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

