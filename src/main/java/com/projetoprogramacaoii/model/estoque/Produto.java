package com.projetoprogramacaoii.model.estoque;

public class Produto {
	
	private String nome;
	private double valor;
	
	public Produto(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected double getValor() {
		return valor;
	}

	protected void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
