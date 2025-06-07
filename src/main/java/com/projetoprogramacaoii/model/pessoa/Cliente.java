package com.projetoprogramacaoii.model.pessoa;

import java.time.LocalDate;
import com.projetoprogramacaoii.model.Identificacao;

public class Cliente extends Usuario implements Identificacao {

    private String cpf;
    private LocalDate dataNascimento;

    public Cliente(String nome, String email, String senha, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public void verQuartosDisponiveis() {
        // A lógica disso ficaria no Controller/Service
    }
    
    @Override
    public String getIdentificacao() {
        return String.format(
                "nome: %s\n" + 
                "cpf: %s\n",
                nome, cpf
        );
    }
    
    @Override
    public String getId() {
        return this.cpf; // fazer depois o id formatado
    }

}