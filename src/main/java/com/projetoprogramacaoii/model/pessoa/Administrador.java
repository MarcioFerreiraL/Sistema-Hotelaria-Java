package com.projetoprogramacaoii.model.pessoa;

import com.projetoprogramacaoii.model.Identificacao;
/*
 * Classe que representa um usuário Administrador no sistema.
 * Herda os atributos e métodos da classe base Usuario.
 */
public class Administrador extends Usuario implements Identificacao{
	
	protected Administrador(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	protected void gerarRelatorios() {
		
	}
	
	@Override
	public String getIdentificacao() {
		return String.format(
				"nome: %s\n" + 
				"email: %s\n"
				, nome, email
				);
	}
	
	@Override
	public String toString() {
	    return "Administrador{" +
	            super.toString() +
	            '}';
	}
}
