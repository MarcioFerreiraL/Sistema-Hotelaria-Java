package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.repository.ClienteRepository;
import com.projetoprogramacaoii.util.ValidacaoException;
import java.io.IOException;
import java.time.LocalDate;

public class ClienteService {

	public void criarCliente(String nome, String email, String senha, String cpf, int anoNascimento) throws ValidacaoException, IOException {

	    if (nome == null || nome.trim().isEmpty()) {
	        throw new ValidacaoException("O nome do cliente não pode estar vazio.");
	    }
	    if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	        throw new ValidacaoException("Formato de email inválido.");
	    }
	    
	    if (senha == null || senha.trim().isEmpty()) {
	        throw new ValidacaoException("A senha não pode estar vazia.");
	    }
	    
        int anoAtual = LocalDate.now().getYear();
        if (anoNascimento > anoAtual || anoNascimento < (anoAtual - 110)) {
            throw new ValidacaoException("Ano de nascimento inválido. Deve ser um ano realista.");
        }
        
        validarCpf(cpf);

        Cliente cliente = new Cliente(nome, email, senha, cpf, anoNascimento);
        ClienteRepository.registrar(cliente);
    }

    private void validarCpf(String cpf) throws ValidacaoException, IOException {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ValidacaoException("CPF inválido. Por favor, digite exatamente 11 números, sem pontos ou traços.");
        }

        if (ClienteRepository.ler().stream().anyMatch(c -> c.getCpf().equals(cpf))) {
            throw new ValidacaoException("CPF já cadastrado no sistema.");
        }
    }
}