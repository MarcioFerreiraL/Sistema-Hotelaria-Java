package com.projetoprogramacaoii.model.pessoa;
/*
 * Classe abstrata que serve como base para todos os tipos de usuários do sistema.
 * Define atributos e comportamentos comuns, como nome, email e senha.
 */
public abstract class Usuario {
	String nome;
	String email;
	String senha;

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
	
	@Override
	public String toString() {
	    return "nome='" + nome + '\'' +
	            ", email='" + email + '\'';
	}
}
