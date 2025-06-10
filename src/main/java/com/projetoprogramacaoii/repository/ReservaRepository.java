package com.projetoprogramacaoii.repository;

import com.projetoprogramacaoii.model.pessoa.Cliente;
import com.projetoprogramacaoii.model.reserva.FormaPagamento;
import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.Reserva;
import java.time.LocalDate;

public class ReservaRepository{

    private final ClienteRepository clienteRepository;
    private final QuartoRepository quartoRepository;

    public ReservaRepository(ClienteRepository clienteRepository, QuartoRepository quartoRepository) {
        this.clienteRepository = clienteRepository;
        this.quartoRepository = quartoRepository;
    }

    public String toLine(Reserva reserva) {
        // Formato: id;cliente;quarto_numero;data;formaPagamento
        return String.format("%s;%",
                reserva.getId(),
                reserva.getCliente().getIdentificacao(),
                String.valueOf(reserva.getQuarto().getNumero()), // Salva apenas o ID do quarto (número)
                reserva.getData().toString(),
                reserva.getFormaPagamento().name()
        );
    }

    public Reserva fromLine(String line) {
        String[] parts = line.split(";");
        String id = parts[0];
        String clienteCpf = parts[1];
        int quartoNumero = Integer.parseInt(parts[2]);
        LocalDate data = LocalDate.parse(parts[3]);
        FormaPagamento formaPagamento = FormaPagamento.valueOf(parts[4]);

   public void reservarQuarto(Cliente cliente, Quarto quarto, LocalDate data, FormaPagamento formaPagamento) {
	   new Reserva(cliente, quarto, data, formaPagamento);
   }
   
    }
}