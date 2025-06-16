package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.repository.ClienteRepository;
import com.projetoprogramacaoii.repository.ReservaRepository;
import com.projetoprogramacaoii.util.ValidacaoException;
import java.io.IOException;
import java.time.LocalDate;

public class ClienteService {

    /*
     * Valida os dados de um novo cliente e, se válidos, o registra no repositório.
     */
	public void criarCliente(String nome, String email, String senha, String cpf, int anoNascimento) throws ValidacaoException, IOException {

		// Regra de negócio: nome não pode ser vazio.
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new ValidacaoException("O nome do cliente não pode estar vazio.");
	    }
	    // Regra de negócio: email deve ter um formato válido.
	    if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	        throw new ValidacaoException("Formato de email inválido.");
	    }
	    // Regra de negócio: senha não pode ser vazia.
	    if (senha == null || senha.trim().isEmpty()) {
	        throw new ValidacaoException("A senha não pode estar vazia.");
	    }
	    // Regra de negócio: ano de nascimento deve ser realista.
        int anoAtual = LocalDate.now().getYear();
        if (anoNascimento > anoAtual || anoNascimento < (anoAtual - 110)) {
            throw new ValidacaoException("Ano de nascimento inválido. Deve ser um ano realista.");
        }
        // Valida o CPF (formato e unicidade)
        validarCpf(cpf);
        // Se todas as validações passaram, cria o objeto e o registra.
        Cliente cliente = new Cliente(nome, email, senha, cpf, anoNascimento);
        ClienteRepository.registrar(cliente);
    }
	// Valida o formato do CPF e verifica se ele já existe no sistema.
    private void validarCpf(String cpf) throws ValidacaoException, IOException {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ValidacaoException("CPF inválido. Por favor, digite exatamente 11 números, sem pontos ou traços.");
        }
        // Regra de negócio: CPF deve ser único.
        if (ClienteRepository.ler().stream().anyMatch(c -> c.getCpf().equals(cpf))) {
            throw new ValidacaoException("CPF já cadastrado no sistema.");
        }
    }
    // Altera um campo específico de um cliente existente.
    public void alterarCliente(String cpf, int campo, String novoValor) throws ValidacaoException, IOException {
        Cliente cliente = ClienteRepository.buscarPorCpf(cpf);
        if (cliente == null) {
            throw new ValidacaoException("Cliente com CPF " + cpf + " não encontrado.");
        }
        // Aplica a alteração com base no campo escolhido
        switch (campo) {
            case 1: // Nome
                if (novoValor == null || novoValor.trim().isEmpty()) {
                    throw new ValidacaoException("O nome não pode ser vazio.");
                }
                cliente.setNome(novoValor);
                break;
            case 2: // Email
                if (novoValor == null || !novoValor.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    throw new ValidacaoException("Formato de email inválido.");
                }
                cliente.setEmail(novoValor);
                break;
            case 3: // Senha
                 if (novoValor == null || novoValor.trim().isEmpty()) {
                    throw new ValidacaoException("A senha não pode ser vazia.");
                }
                cliente.setSenha(novoValor);
                break;
            case 4: // Ano de Nascimento
                try {
                    int anoNascimento = Integer.parseInt(novoValor);
                    int anoAtual = LocalDate.now().getYear();
                    if (anoNascimento > anoAtual || anoNascimento < (anoAtual - 110)) {
                        throw new ValidacaoException("Ano de nascimento inválido.");
                    }
                    cliente.setAnoNascimento(anoNascimento);
                } catch (NumberFormatException e) {
                    throw new ValidacaoException("Ano de nascimento deve ser um número válido.");
                }
                break;
            default:
                throw new ValidacaoException("Campo inválido para alteração.");
        }
        ClienteRepository.atualizar(cliente);
    }
    // Exclui um cliente, mas antes verifica se ele não possui reservas ativas.
    public void excluirCliente(String cpf) throws ValidacaoException, IOException {
        if (ReservaRepository.ler().stream().anyMatch(r -> r.getCliente().getCpf().equals(cpf))) {
            throw new ValidacaoException("Não é possível excluir um cliente com reservas ativas.");
        }
        ClienteRepository.excluir(cpf);
    }
}