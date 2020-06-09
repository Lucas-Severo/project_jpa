package br.com.projeto.domain.enums;

public enum Prioridade {

	BAIXA(1), MEDIA(2), ALTA(3);
	
	private int valor;
	
	private Prioridade(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
	
}
