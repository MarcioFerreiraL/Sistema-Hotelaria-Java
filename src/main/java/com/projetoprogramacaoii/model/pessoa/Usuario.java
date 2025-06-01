package com.projetoprogramacaoii.model.pessoa;

public abstract class Usuario {
	String nome;
	String email;
	String senha;
	
	public boolean login(String nome, String email, String senha) {
		return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIdentificacao() {
		return null;
	}
	
	
}
