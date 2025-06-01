package com.projetoprogramacaoii.model.pessoa;

import com.projetoprogramacaoii.model.Identificacao;

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
	
	
}
