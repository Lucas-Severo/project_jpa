package br.com.projeto.test;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.projeto.domain.connection.ConnectionEntityManager;
import br.com.projeto.domain.dao.PessoaDAO;
import br.com.projeto.domain.dao.TarefaDAO;
import br.com.projeto.domain.enums.Prioridade;
import br.com.projeto.domain.model.Pessoa;
import br.com.projeto.domain.model.Tarefa;

public class TarefaTest {

	public static void main(String[] args) {
		
		EntityManager em = new ConnectionEntityManager().getConnection();
		PessoaDAO pessoaDAO = new PessoaDAO();
		TarefaDAO tarefaDAO = new TarefaDAO();
		
//		Pessoa pessoa = new Pessoa(null, "Teste3", 
//				"teste8@teste.com", 
//				"1234asdf", 
//				LocalDate.of(2000, 5, 10));
//		
//		pessoa = pessoaDAO.save(pessoa);
//		
//		Pessoa pessoa = pessoaDAO.findById(3L);
//		
//		tarefa1.setPessoa(pessoa);
//		tarefa2.setPessoa(pessoa);
		
//		em.getTransaction().begin();
//		em.persist(tarefa1);
//		em.getTransaction().commit();
//		
//		em.getTransaction().begin();
//		em.persist(tarefa2);
//		em.getTransaction().commit();
//		
//		em.close();
		
//		Tarefa tarefa1 = new Tarefa(null, "Curso de java", Prioridade.MEDIA);
//		Tarefa tarefa2 = new Tarefa(null, "Curso de Python", Prioridade.ALTA);
//		Tarefa tarefa3 = new Tarefa(null, "Fazer a tarefa", Prioridade.MEDIA);
//		
		Pessoa pessoa1 = pessoaDAO.findById(1L);
//		
//		tarefa1.setPessoa(pessoa1);
//		tarefa2.setPessoa(pessoa1);
//		
//		Pessoa pessoa2 = pessoaDAO.save(new Pessoa(null, "Fabio", "fabio@email.com", "123456789", LocalDate.of(1990, 1, 7)));
//		
//		tarefa3.setPessoa(pessoa2);
//		
//		tarefaDAO.save(tarefa1);
//		tarefaDAO.save(tarefa2);
//		tarefaDAO.save(tarefa3);
		
//		Tarefa tarefa1 = new Tarefa(5L, "Curso de java", Prioridade.BAIXA);
//		tarefa1.setPessoa(pessoa1);
//		tarefaDAO.save(tarefa1);
	
//		Tarefa tarefa = tarefaDAO.findById(3L);
		
//		List<Tarefa> tarefas = tarefaDAO.findAll();
		
		List<Tarefa> tarefas = tarefaDAO.findByPessoa(1L, Prioridade.ALTA);
		
		tarefas.forEach(tarefa -> {
			System.out.println("Descrição: " + tarefa.getDescricao());
		});
	}
	
}
