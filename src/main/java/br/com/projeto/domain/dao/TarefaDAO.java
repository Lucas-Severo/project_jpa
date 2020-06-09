package br.com.projeto.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.domain.connection.ConnectionEntityManager;
import br.com.projeto.domain.enums.Prioridade;
import br.com.projeto.domain.model.Tarefa;

public class TarefaDAO {

	public Tarefa save(Tarefa tarefa) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		try {
			
			entityManager.getTransaction().begin();
			
			if(tarefa.getId() == null) {
				entityManager.persist(tarefa);				
			} else {
				entityManager.merge(tarefa);
			}
			
			entityManager.getTransaction().commit();
			
		} catch(Exception err) {
			entityManager.getTransaction().rollback();
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return tarefa;
	}
	
	public Tarefa remove(Long id) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
	
		Tarefa tarefa = null;
		
		try {
			
			tarefa = entityManager.find(Tarefa.class, id);
			
			entityManager.getTransaction().begin();
			
			if(tarefa != null) {
				entityManager.remove(tarefa);
			}
			
			entityManager.getTransaction().commit();
		} catch (Exception err) {
			entityManager.getTransaction().rollback();
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return tarefa;
	}
	
	public Tarefa findById(Long id) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		Tarefa tarefa = null;
		
		try {
			
			tarefa = entityManager.find(Tarefa.class, id);
			
		} catch(Exception err) {
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return tarefa;
	}
	
	public List<Tarefa> findAll() {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		List<Tarefa> tarefas = null;
		
		try {
	
			tarefas = entityManager.createQuery("from Tarefa c").getResultList();
			
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return tarefas;
	}
	
	// mostra as tarefas vínculadas a uma pessoa
	// associadas a uma prioridade
	public List<Tarefa> findByPessoa(Long id, Prioridade prioridade) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		List<Tarefa> tarefas = null;
		
		try {
			Query tarefasQuery;
			
			if(prioridade == null) {
				tarefasQuery = entityManager.createQuery("SELECT c FROM Tarefa c WHERE pessoa_id = :id");
				tarefasQuery.setParameter("id", id);
			} else {
				tarefasQuery = entityManager.createQuery("SELECT c FROM Tarefa c WHERE pessoa_id = :id and prioridade = :prioridade");
				tarefasQuery.setParameter("id", id);
				tarefasQuery.setParameter("prioridade", prioridade);
			}
			
			tarefas = tarefasQuery.getResultList();
			
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return tarefas;
	}
	
	// mostra todas as tarefas vínculadas a uma pessoa
	public List<Tarefa> findByPessoa(Long id) {
		return this.findByPessoa(id, null);
	}
}
