package com.projetoprogramacaoii.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.projetoprogramacaoii.model.rh.Funcionario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private static final String NOME_ARQUIVO = "funcionarios.json";
    private static final Path PASTA_DE_DADOS = Path.of("data");
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void inicializarArquivoDeDados() throws IOException {
        if (!Files.exists(PASTA_DE_DADOS)) {
            Files.createDirectories(PASTA_DE_DADOS);
        }

        if (!Files.exists(CAMINHO_ARQUIVO_DE_DADOS)) {
            try (InputStream is = FuncionarioRepository.class.getClassLoader().getResourceAsStream(NOME_ARQUIVO)) {
                if (is == null) {
                    throw new IOException("Arquivo template '" + NOME_ARQUIVO + "' não encontrado nos resources.");
                }
                Files.copy(is, CAMINHO_ARQUIVO_DE_DADOS, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static void registrar(Funcionario funcionario) throws IOException {
        inicializarArquivoDeDados();
        List<Funcionario> funcionarios = ler();
        funcionarios.add(funcionario);

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(funcionarios, writer);
        }
    }

    public static List<Funcionario> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Funcionario>>() {}.getType();
            List<Funcionario> funcionarios = GSON.fromJson(reader, tipoLista);
            return (funcionarios == null) ? new ArrayList<>() : funcionarios;
        }
    }

    public static void atualizarLista(List<Funcionario> funcionarios) throws IOException {
        inicializarArquivoDeDados();
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(funcionarios, writer);
        }
    }
}