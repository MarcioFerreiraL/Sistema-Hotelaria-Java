package com.projetoprogramacaoii.model.pessoa;

import com.projetoprogramacaoii.model.Identificacao;

/**
 * Classe que representa um Cliente do hotel.
 * Herda de Usuario, reutilizando os campos de nome, email e senha.
 * Implementa a interface Identificacao, fornecendo uma forma de ser unicamente
 * identificado no sistema (através do CPF).
 */
public class Cliente extends Usuario implements Identificacao {

    private String cpf;
    private int anoNascimento;

    public Cliente(String nome, String email, String senha, String cpf, int anoNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.anoNascimento = anoNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    
    public String getIdentificacao() {
        return String.format(
                "nome: %s\n" + 
                "cpf: %s\n",
                nome, getId()
        );
    }
    
    @Override
    public String getId() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + getCpf() + '\'' + 
                ", " + super.toString() +
                '}';
    }
}