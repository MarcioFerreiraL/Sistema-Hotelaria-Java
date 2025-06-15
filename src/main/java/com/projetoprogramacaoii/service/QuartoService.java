package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.TipoQuarto;
import com.projetoprogramacaoii.repository.QuartoRepository;
import com.projetoprogramacaoii.util.ValidacaoException;
import java.io.IOException;

public class QuartoService {

	public void criarQuarto(int numero, TipoQuarto tipo, boolean ocupado) throws ValidacaoException, IOException {
        if (numero <= 0) {
            throw new ValidacaoException("O número do quarto deve ser um valor positivo.");
        }

        validarNumeroQuarto(numero);

        Quarto quarto = new Quarto(numero, tipo, ocupado);
        QuartoRepository.registrar(quarto);
    }

    private void validarNumeroQuarto(int numero) throws ValidacaoException, IOException {
        if (QuartoRepository.ler().stream().anyMatch(q -> q.getNumero() == numero)) {
            throw new ValidacaoException("Número de quarto já existente.");
        }
    }
}