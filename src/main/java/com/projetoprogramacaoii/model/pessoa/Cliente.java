package com.projetoprogramacaoii.model.pessoa;

import com.projetoprogramacaoii.model.Identificacao;

public class Cliente extends Usuario implements Identificacao{

	private String cpf;
	
	public Cliente(String nome, String email, String senha, String cpf) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void verQuartosDisponiveis() {
		
	}
	
	@Override
	public String getIdentificacao() {
		return String.format(
				"nome: %s\n" + 
				"cpf: %s\n"
				, nome, cpf
				);
	}
	
	public String getId() {
		return String.format("Id: %s\n", cpf); //mudar para cpf formatado depois
	}
}
