package br.com.projeto.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.projeto.domain.model.Pessoa;

public class PessoaTest {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoProject");
		EntityManager em = emf.createEntityManager();
	
		Pessoa pessoa = new Pessoa(null, "Teste", "teste@teste.com", "1234asdf", LocalDate.of(2000, 5, 10));
		
		em.getTransaction().begin();
		
		em.persist(pessoa);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
				
	}
	
}
