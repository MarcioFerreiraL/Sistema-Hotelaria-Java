package com.projetoprogramacaoii.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.projetoprogramacaoii.model.financeiro.Lancamento;

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

public class LancamentoRepository {

    private static final String NOME_ARQUIVO = "lancamentos.json";
    private static final Path PASTA_DE_DADOS = Path.of("data");
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void inicializarArquivoDeDados() throws IOException {
        if (!Files.exists(PASTA_DE_DADOS)) {
            Files.createDirectories(PASTA_DE_DADOS);
        }

        if (!Files.exists(CAMINHO_ARQUIVO_DE_DADOS)) {
            try (InputStream is = LancamentoRepository.class.getClassLoader().getResourceAsStream(NOME_ARQUIVO)) {
                if (is == null) {
                    throw new IOException("Arquivo template '" + NOME_ARQUIVO + "' não encontrado nos resources.");
                }
                Files.copy(is, CAMINHO_ARQUIVO_DE_DADOS, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private static void registrar(Lancamento lancamento) throws IOException {
        inicializarArquivoDeDados();
        List<Lancamento> lancamentos = ler();
        lancamentos.add(lancamento);
        atualizarLista(lancamentos);
    }

    public static void registrarReceita(Lancamento lancamento) throws IOException {
        registrar(lancamento);
    }

    public static void registrarDespesa(Lancamento lancamento) throws IOException {
        registrar(lancamento);
    }

    public static List<Lancamento> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Lancamento>>() {}.getType();
            List<Lancamento> lancamentos = GSON.fromJson(reader, tipoLista);
            return (lancamentos == null) ? new ArrayList<>() : lancamentos;
        }
    }

    public static void atualizarLista(List<Lancamento> lancamentos) throws IOException {
        inicializarArquivoDeDados();
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(lancamentos, writer);
        }
    }
}