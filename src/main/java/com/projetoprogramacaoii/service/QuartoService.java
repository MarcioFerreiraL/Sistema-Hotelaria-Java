package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.TipoQuarto;
import com.projetoprogramacaoii.repository.QuartoRepository;
import com.projetoprogramacaoii.util.ValidacaoException;
import java.io.IOException;

public class QuartoService {

	public void criarQuarto(int numero, TipoQuarto tipo, boolean ocupado) throws ValidacaoException, IOException {
        // Regra: O número do quarto deve ser um valor positivo.
        if (numero <= 0) {
            throw new ValidacaoException("O número do quarto deve ser maior que zero.");
        }

        // Regra: O número do quarto deve ser único.
        // Acessa o repositório para verificar se já existe um quarto com o mesmo número.
        validarNumeroQuarto(numero);

        Quarto quarto = new Quarto(numero, tipo, ocupado);
        QuartoRepository.registrar(quarto);
    }

    private void validarNumeroQuarto(int numero) throws ValidacaoException, IOException {
        if (QuartoRepository.ler().stream().anyMatch(q -> q.getNumero() == numero)) {
            throw new ValidacaoException("Número de quarto já existente.");
        }
    }
    
 public void alterarQuarto(int numero, TipoQuarto novoTipo) throws ValidacaoException, IOException {
     Quarto quarto = QuartoRepository.buscarPorNumero(numero);
     if (quarto == null) {
         throw new ValidacaoException("Quarto número " + numero + " não encontrado.");
     }
     quarto.setTipo(novoTipo);
     QuartoRepository.atualizar(quarto);
 }

 public void excluirQuarto(int numero) throws ValidacaoException, IOException {
     Quarto quarto = QuartoRepository.buscarPorNumero(numero);
     if (quarto == null) {
         throw new ValidacaoException("Quarto número " + numero + " não encontrado.");
     }
     if (quarto.isOcupado()) {
         throw new ValidacaoException("Não é possível excluir um quarto que está ocupado/reservado.");
     }
     QuartoRepository.excluir(numero);
 }
}