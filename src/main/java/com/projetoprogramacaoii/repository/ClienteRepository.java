package com.projetoprogramacaoii.repository;

import com.projetoprogramacaoii.model.pessoa.Cliente;
import java.time.LocalDate;

public class ClienteRepository extends TxtRepository<Cliente, String> {
    
    private static final String DELIMITER = ";";

    public ClienteRepository() {
        super("clientes.txt", Cliente::getCpf);
    }

    @Override
    protected String toLine(Cliente cliente) {
        // Formato: cpf;nome;email;senha;dataNascimento
        return String.join(DELIMITER,
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getDataNascimento().toString()
        );
    }

    @Override
    protected Cliente fromLine(String line) {
        String[] parts = line.split(DELIMITER);
        String cpf = parts[0];
        String nome = parts[1];
        String email = parts[2];
        String senha = parts[3];
        LocalDate dataNascimento = LocalDate.parse(parts[4]);
        
        // O construtor do Cliente não permite criar um objeto vazio, então usamos o construtor existente
        Cliente cliente = new Cliente(nome, email, senha, cpf, dataNascimento);
        return cliente;
    }

	public Cliente encontrarId(String clienteCpf) {
		return null;
	}
}