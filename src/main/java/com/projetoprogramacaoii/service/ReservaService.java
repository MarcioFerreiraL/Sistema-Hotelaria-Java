package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.model.reserva.FormaPagamento;
import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.Reserva;
import com.projetoprogramacaoii.repository.QuartoRepository;
import com.projetoprogramacaoii.repository.ReservaRepository;
import com.projetoprogramacaoii.util.ValidacaoException;
import java.io.IOException;
import java.time.LocalDate;

public class ReservaService {
	// Orquestra o processo complexo de criação de uma reserva.
    public Reserva criarReserva(Cliente cliente, Quarto quarto, FormaPagamento formaPagamento) throws ValidacaoException, IOException {
        // Regra de negócio: Cliente deve ser maior de 18 anos
        if (LocalDate.now().getYear() - cliente.getAnoNascimento() < 18) {
            throw new ValidacaoException("Cliente deve ser maior de 18 anos para fazer uma reserva.");
        }
        // Regra de negócio: Quarto não pode estar ocupado.
        if (quarto.isOcupado()) {
            throw new ValidacaoException("Quarto " + quarto.getNumero() + " já está ocupado.");
        }
        // 1. Cria e registra a reserva
        String id = cliente.getCpf() + quarto.getNumero();
        Reserva novaReserva = new Reserva(id, cliente, quarto, formaPagamento);
        ReservaRepository.registrar(novaReserva);
        
        // 2. Atualiza o status do quarto para ocupado
        quarto.reservar();
        QuartoRepository.atualizar(quarto);
        
        // 3. Lança a receita financeira correspondente à reserva
        try {
            String descricaoReceita = "Receita da reserva do quarto " + quarto.getNumero() + " para o cliente " + cliente.getNome();
            LancamentoService.criarReceita(quarto.getValor(), descricaoReceita, LocalDate.now());
        } catch (IOException e) {
            throw new IOException("A reserva foi criada, mas falhou ao registrar a receita financeira. Contate o suporte.", e);
        }
        
        System.out.println("Reserva criada com sucesso!");
        return novaReserva;
    }
}