package br.com.projeto.test;

import java.time.LocalDate;
import java.util.List;

import br.com.projeto.domain.dao.PessoaDAO;
import br.com.projeto.domain.model.Pessoa;

public class PessoaTest {

	public static void main(String[] args) {
		
		PessoaDAO pessoaDAO = new PessoaDAO();
	
		//Pessoa pessoa = new Pessoa(null, "Teste", "teste3@teste.com", "1234asdf", LocalDate.of(2000, 5, 10));
		//Pessoa pessoa = new Pessoa(null, "Teste3", "teste8@teste.com", "1234asdf", LocalDate.of(2000, 5, 10));
		
		//pessoaDAO.save(pessoa);
				
		//pessoaDAO.remove(1L);
		
		//Pessoa pessoa = pessoaDAO.findById(3L);
		//Pessoa pessoa = pessoaDAO.findByEmail("teste2@teste.com");
		
		List<Pessoa> pessoas = pessoaDAO.findAll();
		
		pessoas.forEach(person -> {
			System.out.println(person.getId());
			System.out.println(person.getNome());
			System.out.println(person.getEmail());
		});

	}
	
}
