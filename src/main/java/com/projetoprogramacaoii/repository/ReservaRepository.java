package com.projetoprogramacaoii.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.projetoprogramacaoii.model.reserva.Reserva; // <-- MUDANÇA: Trabalha com o tipo Reserva
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/*
 * Repositório responsável pela persistência dos dados de Reservas.
 */
public class ReservaRepository {

    // Constantes específicas para o repositório de reservas.
    private static final String NOME_ARQUIVO = "reservas.json";
    private static final Path PASTA_DE_DADOS = Paths.get("data");
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void inicializarArquivoDeDados() throws IOException {
        if (Files.notExists(PASTA_DE_DADOS)) {
            Files.createDirectories(PASTA_DE_DADOS);
        }
        if (Files.notExists(CAMINHO_ARQUIVO_DE_DADOS)) {
            try (InputStream inputStream = ReservaRepository.class.getClassLoader().getResourceAsStream(NOME_ARQUIVO)) {
                if (inputStream == null) {
                    throw new IOException("Arquivo de template '" + NOME_ARQUIVO + "' não encontrado nos recursos.");
                }
                Files.copy(inputStream, CAMINHO_ARQUIVO_DE_DADOS, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    /*
     * Lê todas as reservas do arquivo JSON.
     */
    public static List<Reserva> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Reserva>>() {}.getType();
            List<Reserva> reservas = GSON.fromJson(reader, tipoLista);
            return (reservas == null) ? new ArrayList<>() : reservas;
        }
    }

    /*
     * Sobrescreve o arquivo JSON com uma nova lista de reservas.
     */
    private static void atualizarLista(List<Reserva> reservas) throws IOException {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(reservas, writer);
        }
    }

    /*
     * Remove uma reserva com base no seu ID único.
     */
    public static void excluir(String id) throws IOException {
        List<Reserva> reservas = ler();
        boolean removido = reservas.removeIf(reserva -> reserva.getId().equals(id));
        if (removido) {
            atualizarLista(reservas);
        }
    }
    
    /*
     * Adiciona uma nova reserva ao arquivo JSON.
     */
    public static void registrar(Reserva reserva) throws IOException {
        List<Reserva> reservas = ler();
        reservas.add(reserva);
        atualizarLista(reservas);
    }
}