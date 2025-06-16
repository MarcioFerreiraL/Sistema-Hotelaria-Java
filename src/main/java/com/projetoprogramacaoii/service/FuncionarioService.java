package com.projetoprogramacaoii.service;

import java.io.IOException;
import java.time.LocalDate;
import com.projetoprogramacaoii.model.rh.Funcionario;
import com.projetoprogramacaoii.model.rh.TipoContrato;
import com.projetoprogramacaoii.repository.FuncionarioRepository;
import com.projetoprogramacaoii.util.ValidacaoException;

public class FuncionarioService {

	public void criarFuncionario(String nome, String cpf, String cargo, double salario, TipoContrato tipoContrato) throws ValidacaoException, IOException {
		// VALIDAÇÕES
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
	
	public static void alterarFuncionario(String cpf, int campo, Object novoValor) throws ValidacaoException, IOException {
	    Funcionario funcionario = FuncionarioRepository.buscarPorCpf(cpf);
	    if (funcionario == null) {
	        throw new ValidacaoException("Funcionário com CPF " + cpf + " não encontrado.");
	    }

	    switch (campo) {
	        case 1: // Nome
	            funcionario.setNome((String) novoValor);
	            break;
	        case 2: // Cargo
	            funcionario.setCargo((String) novoValor);
	            break;
	        case 3: // Salário
	            double novoSalario = (Double) novoValor;
	            if (novoSalario <= 0) {
	                throw new ValidacaoException("O salário deve ser um valor positivo.");
	            }
	            funcionario.setSalario(novoSalario);
	            break;
	        case 4: // Tipo de Contrato
	            funcionario.setTipoContrato((TipoContrato) novoValor);
	            break;
	        default:
	            throw new ValidacaoException("Campo inválido para alteração.");
	    }
	    FuncionarioRepository.atualizar(funcionario);
	}

	public static void excluirFuncionario(String cpf) throws IOException {
	    FuncionarioRepository.excluir(cpf);
	}
}