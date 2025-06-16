package com.projetoprogramacaoii.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.projetoprogramacaoii.model.pessoa.Cliente;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
// Esta classe gerencia todas as operações de leitura e escrita relacionadas
public class ClienteRepository {
	// Constante que define o nome do arquivo de dados.
    private static final String NOME_ARQUIVO = "clientes.json";
    // Define o caminho para a pasta 'data'
    private static final Path PASTA_DE_DADOS = Path.of("data");
    // Combina a pasta de dados com o nome do arquivo para obter o caminho completo.
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    // Instância da biblioteca Gson, usada para converter objetos Java para JSON e vice-versa.
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Garante que a pasta de dados e o arquivo JSON existam. Se não existirem, são criados.
    private static void inicializarArquivoDeDados() throws IOException {
        if (!Files.exists(PASTA_DE_DADOS)) {
            Files.createDirectories(PASTA_DE_DADOS);
        }
        if (!Files.exists(CAMINHO_ARQUIVO_DE_DADOS)) {
             try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
                writer.write("[]");
            }
        }
    }
    // Adiciona um novo cliente à lista de clientes e salva a lista atualizada no arquivo JSON.
    public static void registrar(Cliente cliente) throws IOException {
        inicializarArquivoDeDados();
        List<Cliente> clientes = ler();
        clientes.add(cliente);
        atualizarLista(clientes);
    }
    // Lê o arquivo JSON e o converte em uma lista de objetos Cliente.
    public static List<Cliente> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Cliente>>() {}.getType();
            List<Cliente> clientes = GSON.fromJson(reader, tipoLista);
            return (clientes == null) ? new ArrayList<>() : clientes;
        }
    }
    // Sobrescreve o arquivo JSON com uma nova lista de clientes.
    public static void atualizarLista(List<Cliente> clientes) throws IOException {
        inicializarArquivoDeDados();
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(clientes, writer);
        }
    }
    // Busca um cliente específico pelo seu CPF.
    public static Cliente buscarPorCpf(String cpf) throws IOException {
        return ler().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
    // Atualiza os dados de um cliente que já existe na base de dados.
    public static void atualizar(Cliente clienteAtualizado) throws IOException {
        List<Cliente> clientes = ler();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(clienteAtualizado.getCpf())) {
                clientes.set(i, clienteAtualizado);
                atualizarLista(clientes);
                return;
            }
        }
    }
    // Remove um cliente do arquivo JSON com base no seu CPF.
    public static void excluir(String cpf) throws IOException {
        List<Cliente> clientes = ler();
        boolean removido = clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
        if (removido) {
            atualizarLista(clientes);
        }
    }
}