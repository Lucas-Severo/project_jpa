package br.com.projeto.todo;

import br.com.projeto.domain.dao.PessoaDAO;
import br.com.projeto.domain.dao.TarefaDAO;
import br.com.projeto.domain.enums.Prioridade;
import br.com.projeto.domain.model.Pessoa;
import br.com.projeto.domain.model.Tarefa;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	PessoaDAO pessoaDAO = new PessoaDAO();
    	TarefaDAO tarefaDAO = new TarefaDAO();
    	
        Pessoa pessoa = null;
        int opc, dia, mes, ano;
        Prioridade prioridade = null;
        Long id;
        String descricao, nome, email, senha;
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        
        while(continuar) {
	        while(pessoa == null) {
		        System.out.println("\n1) Fazer login");
		        System.out.println("2) Cadastrar");
		        System.out.println("3) Mostrar as pessoas cadastradas");
		        System.out.println("4) Sair");
		        System.out.print(">>> ");
		        
		        opc = sc.nextInt();
		        
		        if(opc == 1) {
		        	sc.nextLine();
		        	System.out.print("Email: ");
		        	email = sc.nextLine();
		        	
		        	System.out.print("Senha: ");
		        	senha = sc.nextLine();
		        	
		        	Pessoa login = pessoaDAO.findByEmail(email);
		        	
		        	if(login != null) {
		        		if(login.getSenha().equals(senha)) {
		        			System.out.println("\nLogin realizado com sucesso!\n");
		        			pessoa = login;
		        		} else {
		        			System.err.println("\nEmail ou senha incorretos\n");
		        		}
		        	} else {
	        			System.err.println("\nEmail ou senha incorretos\n");
		        	}
		        } else if(opc == 2) {
		        	sc.nextLine();
		        	System.out.print("Nome: ");
		        	nome = sc.nextLine();
		        	
		        	System.out.print("Email: ");
		        	email = sc.nextLine();
		        	
		        	System.out.print("Senha: ");
		        	senha = sc.nextLine();
		        	
		        	System.out.println("\n#### DATA DE NASCIMENTO ####\n");
		        	
		        	System.out.print("Dia: ");
		        	dia = sc.nextInt();
		        	
		        	System.out.print("Mes: ");
		        	mes = sc.nextInt();
		        	
		        	System.out.print("Ano: ");
		        	ano = sc.nextInt();
		        	
		        	Pessoa cadastro = new Pessoa(null, nome, email, senha, LocalDate.of(ano, mes, dia));
		        	cadastro = pessoaDAO.save(cadastro);
		        	
		        	if(cadastro != null) {
		        		System.out.println("\nCadastro realizado com sucesso!\n");
		        	}
		 
		        } else if (opc == 3) {
		        	
		        	System.out.println("\nPessoas cadastras\n");
		        	
		        	List<Pessoa> pessoas = pessoaDAO.findAll();
		        	pessoas.forEach(p -> {
		        		System.out.println("\nNome: " + p.getNome());
		        		System.out.println("Email: " + p.getEmail());
		        	});
		        } else if (opc == 4) {
		        	continuar = false;
		        	break;
		        }
	        }
	        
	        if(continuar) {
	        	while(true) {
	        		System.out.println("1) Listar as tarefas");
	        		System.out.println("2) Cadastrar uma tarefa");
	        		System.out.println("3) Remover uma tarefa");
	        		System.out.println("4) Mostrar pela prioridade");
	        		System.out.println("5) Logout");
	        		System.out.print(">>> ");
	        		
	        		opc = sc.nextInt();
	        		
	        		if(opc == 1) {
	        			System.out.println("\nListando todas as tarefas\n");
	        			
	        			List<Tarefa> tarefas = tarefaDAO.findByPessoa(pessoa.getId());
	        			tarefas.forEach(tarefa -> {
	        				System.out.println("Id: " + tarefa.getId());
	        				System.out.println("Descricao: " + tarefa.getDescricao());
	        				System.out.println("Prioridade: " + tarefa.getPrioridade());
	        				System.out.println("\n");
	        			});
	        		} else if(opc == 2) {
	        			sc.nextLine();
	        			System.out.println("\nCadastrando uma tarefa\n");
	        			
	        			System.out.print("Descricao: ");
	        			descricao = sc.nextLine();
	        			
	        			do {
		        			System.out.println("\n#### Prioridade ####\n");
		        			System.out.println("1) Baixa");
		        			System.out.println("2) Media");
		        			System.out.println("3) Alta");
		        			System.out.print(">>> ");
		        			
		        			opc = sc.nextInt();
	        			} while(opc < 1 || opc > 3);
	        			
	        			if(opc == 1) {
	        				prioridade = Prioridade.BAIXA;
	        			} else if(opc == 2) {
	        				prioridade = Prioridade.MEDIA;
	        			} else if(opc == 3) {
        					prioridade = Prioridade.ALTA;
        				}
	        			
	        			Tarefa tarefa = new Tarefa(null, descricao, prioridade);
	        			tarefa.setPessoa(pessoa);
	        					
	        			tarefaDAO.save(tarefa);
	        			System.out.println("\nTarefa cadastrada com sucesso!\n");
	        		} else if(opc == 3) {
	        			System.out.println("\nRemovendo uma tarefa\n");
	        			System.out.print("Id: ");
	        			
	        			id = sc.nextLong();
	        			Tarefa tarefa = tarefaDAO.remove(pessoa.getId(), id);
	        		
	        		} else if(opc == 4) {
	        			System.out.println("\nMostrar a tarefa pela prioridade\n");
	        			
	        			do {
		        			System.out.println("1) Baixa");
		        			System.out.println("2) Media");
		        			System.out.println("3) Alta");
		        			System.out.print(">>> ");
		        			opc = sc.nextInt();
	        			} while(opc < 1 || opc > 3);
	        			
	        			List<Tarefa> tarefas;
	        			
	        			tarefas = tarefaDAO.findByPessoa(pessoa.getId(), Prioridade.values()[opc-1]);
	        
	        			if(tarefas != null) {
	        				System.out.println("\nMostrando as tarefas\n");
		        			tarefas.forEach(tarefa -> {
		        				System.out.println("Id: " + tarefa.getId());
		        				System.out.println("Descricao: " + tarefa.getDescricao());
		        				System.out.println("Prioridade: " + tarefa.getPrioridade());
		        				System.out.println("\n");
		        			});
	        			}
	        		} else if(opc == 5) {
	        			pessoa = null;
	        			break;
	        		}
	        	}
	        }
        }
        
        sc.close();
   }
}
