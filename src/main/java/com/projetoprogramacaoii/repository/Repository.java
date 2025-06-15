package com.projetoprogramacaoii.repository;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    void registrar(T objeto) throws IOException;
    List<T> ler() throws IOException;
    void atualizar(List<T> objetos) throws IOException;
}