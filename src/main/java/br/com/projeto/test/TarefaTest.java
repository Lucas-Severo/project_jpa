package br.com.projeto.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.projeto.domain.connection.ConnectionEntityManager;
import br.com.projeto.domain.dao.PessoaDAO;
import br.com.projeto.domain.enums.Prioridade;
import br.com.projeto.domain.model.Pessoa;
import br.com.projeto.domain.model.Tarefa;

public class TarefaTest {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionEntityManager().getConnection();
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		Tarefa tarefa1 = new Tarefa(null, "Curso de java", Prioridade.MEDIA);
		Tarefa tarefa2 = new Tarefa(null, "Curso de Python", Prioridade.ALTA);
		
		Pessoa pessoa = new Pessoa(null, "Teste3", 
				"teste8@teste.com", 
				"1234asdf", 
				LocalDate.of(2000, 5, 10));
		
		pessoa = pessoaDAO.save(pessoa);
		
		//Pessoa pessoa = pessoaDAO.findById(3L);
		
		tarefa1.setPessoa(pessoa);
		tarefa2.setPessoa(pessoa);
		
		em.getTransaction().begin();
		em.persist(tarefa1);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		em.persist(tarefa2);
		em.getTransaction().commit();
		
		em.close();
		
	}
	
}
