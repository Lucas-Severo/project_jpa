package br.com.projeto.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.domain.connection.ConnectionEntityManager;
import br.com.projeto.domain.model.Pessoa;

public class PessoaDAO {

	// atualiza e salva uma entidade
	public Pessoa save(Pessoa pessoa) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		try {
			entityManager.getTransaction().begin();
			
			if(pessoa.getId() == null) {
				entityManager.persist(pessoa);
			} else {
				entityManager.merge(pessoa);
			}
			
			entityManager.getTransaction().commit();
		} catch (Exception err) {
			entityManager.getTransaction().rollback();
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return pessoa;
	}
	
	public Pessoa remove(Long id) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
	
		Pessoa pessoa = null;
		
		try {
			pessoa = entityManager.find(Pessoa.class, id);
			
			entityManager.getTransaction().begin();
			
			if(pessoa != null) {
				entityManager.remove(pessoa);
			}
			
			entityManager.getTransaction().commit();
		} catch (Exception err) {
			entityManager.getTransaction().rollback();
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return pessoa;
	}
	
	public Pessoa findById(Long id) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		Pessoa pessoa = null;
		
		try {
			
			pessoa = entityManager.find(Pessoa.class, id);
			
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return pessoa;
	}
	
	public Pessoa findByEmail(String email) {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
		
		Pessoa pessoa = null;
		
		try {
			
			Query pessoaQuery = entityManager.createQuery("SELECT c FROM Pessoa c WHERE c.email = :email", Pessoa.class);
			pessoaQuery.setParameter("email", email);
			
			pessoa = (Pessoa) pessoaQuery.getResultList().get(0);
			
		} catch (Exception err) {
			System.out.println(err.getMessage());
		} finally {
			entityManager.close();
		}
		
		return pessoa;
	}
	
	public List<Pessoa> findAll() {
		EntityManager entityManager = new ConnectionEntityManager().getConnection();
	
		List<Pessoa> pessoas = null;
		
		try {
			
			pessoas = entityManager.createQuery("from Pessoa c").getResultList();
			
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return pessoas;
	}
	
}
