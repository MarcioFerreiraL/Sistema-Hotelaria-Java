package com.projetoprogramacaoii.service;

import java.io.IOException;
import java.time.LocalDate;
import com.projetoprogramacaoii.model.rh.Funcionario;
import com.projetoprogramacaoii.model.rh.TipoContrato;
import com.projetoprogramacaoii.repository.FuncionarioRepository;
import com.projetoprogramacaoii.util.ValidacaoException;

public class FuncionarioService {

	public void criarFuncionario(String nome, String cpf, String cargo, double salario, TipoContrato tipoContrato) throws ValidacaoException, IOException {
		// VALIDAÇÕES ADICIONADAS
		if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome do funcionário não pode estar vazio.");
        }
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ValidacaoException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
        if (FuncionarioRepository.ler().stream().anyMatch(f -> f.getCpf().equals(cpf))) {
            throw new ValidacaoException("CPF de funcionário já cadastrado no sistema.");
        }
        if (salario <= 0) {
            throw new ValidacaoException("O salário deve ser um valor positivo.");
        }
		
		Funcionario funcionario = new Funcionario(nome, cpf, cargo, salario, tipoContrato);
		
		try {
			// Lançar o salário como despesa
			LancamentoService.criarDespesa(salario, String.format("Salario do funcionario %s, cargo %s", nome, cargo), LocalDate.now());
			// Registrar o funcionário
			FuncionarioRepository.registrar(funcionario);
		} catch (IOException e) {
			throw new IOException("Falha ao registrar funcionário ou seu salário no sistema.", e);
		}
	}
}