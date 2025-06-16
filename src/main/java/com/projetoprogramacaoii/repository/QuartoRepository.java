package com.projetoprogramacaoii.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.projetoprogramacaoii.model.reserva.Quarto;

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

public class QuartoRepository {

    private static final String NOME_ARQUIVO = "quartos.json";
    private static final Path PASTA_DE_DADOS = Path.of("data");
    private static final Path CAMINHO_ARQUIVO_DE_DADOS = PASTA_DE_DADOS.resolve(NOME_ARQUIVO);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void inicializarArquivoDeDados() throws IOException {
        if (!Files.exists(PASTA_DE_DADOS)) {
            Files.createDirectories(PASTA_DE_DADOS);
        }

        if (!Files.exists(CAMINHO_ARQUIVO_DE_DADOS)) {
            try (InputStream is = QuartoRepository.class.getClassLoader().getResourceAsStream(NOME_ARQUIVO)) {
                if (is == null) {
                    throw new IOException("Arquivo template '" + NOME_ARQUIVO + "' não encontrado nos resources.");
                }
                Files.copy(is, CAMINHO_ARQUIVO_DE_DADOS, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static void registrar(Quarto quarto) throws IOException {
        inicializarArquivoDeDados();
        List<Quarto> quartos = ler();
        quartos.add(quarto);
        atualizarLista(quartos);
    }

    public static List<Quarto> ler() throws IOException {
        inicializarArquivoDeDados();
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            Type tipoLista = new TypeToken<ArrayList<Quarto>>() {}.getType();
            List<Quarto> quartos = GSON.fromJson(reader, tipoLista);
            return (quartos == null) ? new ArrayList<>() : quartos;
        }
    }

    public static void atualizarLista(List<Quarto> quartos) throws IOException {
        inicializarArquivoDeDados();
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO_DE_DADOS.toFile())) {
            GSON.toJson(quartos, writer);
        }
    }

    public static void atualizar(Quarto quartoAtualizado) throws IOException {
        List<Quarto> quartos = ler();
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == quartoAtualizado.getNumero()) {
                quartos.set(i, quartoAtualizado);
                atualizarLista(quartos);
                return;
            }
        }
    }
    
    public static Quarto buscarPorNumero(int numero) throws IOException {
        return ler().stream()
                .filter(q -> q.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public static void excluir(int numero) throws IOException {
        List<Quarto> quartos = ler();
        boolean removido = quartos.removeIf(q -> q.getNumero() == numero);
        if (removido) {
            atualizarLista(quartos);
        }
    }
}