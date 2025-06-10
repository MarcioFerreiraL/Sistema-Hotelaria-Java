package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.model.reserva.FormaPagamento;
import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.Reserva;
import com.projetoprogramacaoii.repository.ClienteRepository;
import com.projetoprogramacaoii.repository.QuartoRepository;
import com.projetoprogramacaoii.repository.ReservaRepository;
import java.time.LocalDate;
import java.time.Period;

public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final QuartoRepository quartoRepository;
    private final ClienteRepository clienteRepository;
    private final FinanceiroService financeiroService;

    public ReservaService(ReservaRepository reservaRepository, QuartoRepository quartoRepository, ClienteRepository clienteRepository, FinanceiroService financeiroService) {
        this.reservaRepository = reservaRepository;
        this.quartoRepository = quartoRepository;
        this.clienteRepository = clienteRepository;
        this.financeiroService = financeiroService;
    }

    public Reserva criarReserva(String clienteCpf, int numeroQuarto, LocalDate dataReserva, FormaPagamento formaPagamento) {
        // Busca as entidades
        Cliente cliente = clienteRepository.encontrarId(clienteCpf);
        Quarto quarto = quartoRepository.encontrarId(numeroQuarto);
        
        // verifica se a idade é maior que 18
        if (Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears() < 18) {
            throw new IllegalStateException("Cliente deve ser maior de 18 anos para fazer uma reserva.");
        }
        
        // ver se o quarto esta ocupado
        
        if (quarto.isOcupado() == true) {
            throw new IllegalStateException("Quarto " + numeroQuarto + " já está ocupado.");
        }

        // 3. Orquestra as ações
        Reserva novaReserva = new Reserva((Cliente.cpf + Quarto.numero), cliente, quarto, dataReserva, formaPagamento);
        novaReserva.setCliente(cliente);
        novaReserva.setQuarto(quarto);
        novaReserva.setData(dataReserva);
        novaReserva.setFormaPagamento(formaPagamento);
        
        // Salva a reserva
        reservaRepository.salvar(novaReserva);

        // Atualiza o status do quarto
        quarto.reservar();
        quartoRepository.salvar(quarto);

        // Registra o lançamento financeiro
        String descricaoLancamento = "Receita da reserva do quarto " + quarto.getNumero() + " para o cliente " + cliente.getNome();
        financeiroService.registrarReceita(quarto.getValor(), descricaoLancamento, dataReserva);

        System.out.println("Reserva criada com sucesso!");
        return novaReserva;
    }
}