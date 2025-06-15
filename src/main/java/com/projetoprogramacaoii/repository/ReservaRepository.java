package com.projetoprogramacaoii.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.projetoprogramacaoii.model.reserva.Reserva;

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

public class ReservaRepository {

    private static final String NOME_ARQUIVO = "reservas.json";
    private static final Path PASTA_DE_DADOS = Path.of("data");
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void inicializarArquivoDeDados() throws IOException {
        if (!Files.exists(PASTA_DE_DADOS)) {
            Files.createDirectories(PASTA_DE_DADOS);
        }

        if (!Files.exists(CAMINHO_ARQUIVO_DE_DADOS)) {
            try (InputStream is = ReservaRepository.class.getClassLoader().getResourceAsStream(NOME_ARQUIVO)) {
                if (is == null) {
                    throw new IOException("Arquivo template '" + NOME_ARQUIVO + "' não encontrado nos resources.");
                }
                Files.copy(is, CAMINHO_ARQUIVO_DE_DADOS, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static void registrar(Reserva reserva) throws IOException {
        inicializarArquivoDeDados();
        List<Reserva> reservas = ler();
        reservas.add(reserva);
        atualizarLista(reservas);
    }

    public static List<Reserva> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Reserva>>() {}.getType();
            List<Reserva> reservas = GSON.fromJson(reader, tipoLista);
            return (reservas == null) ? new ArrayList<>() : reservas;
        }
    }

    public static void atualizarLista(List<Reserva> reservas) throws IOException {
        inicializarArquivoDeDados();
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(reservas, writer);
        }
    }
}