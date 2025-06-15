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

public class ClienteRepository {

    private static final String NOME_ARQUIVO = "clientes.json";
    private static final Path PASTA_DE_DADOS = Path.of("data");
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

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

    public static void registrar(Cliente cliente) throws IOException {
        inicializarArquivoDeDados();
        List<Cliente> clientes = ler();
        clientes.add(cliente);
        atualizarLista(clientes);
    }

    public static List<Cliente> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Cliente>>() {}.getType();
            List<Cliente> clientes = GSON.fromJson(reader, tipoLista);
            return (clientes == null) ? new ArrayList<>() : clientes;
        }
    }

    public static void atualizarLista(List<Cliente> clientes) throws IOException {
        inicializarArquivoDeDados();
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(clientes, writer);
        }
    }
}