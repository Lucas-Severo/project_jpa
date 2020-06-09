package br.com.projeto.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.projeto.domain.enums.Prioridade;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "descricao", nullable = false, length = 255)
	private String descricao;
	
	@Enumerated
	@Column(name = "prioridade", nullable = false)
	private Prioridade prioridade;
	
	@ManyToOne
	private Pessoa pessoa;
	
	public Tarefa() {
		
	}

	public Tarefa(Long id, String descricao, Prioridade prioridade) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.prioridade = prioridade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
