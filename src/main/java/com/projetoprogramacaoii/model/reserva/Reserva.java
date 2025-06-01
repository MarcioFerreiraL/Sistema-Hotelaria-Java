package com.projetoprogramacaoii.model.reserva;

import java.time.LocalDate;
import com.projetoprogramacaoii.model.pessoa.Cliente;

public class Reserva {
	
	private Cliente cliente;
	private Quarto quarto;
	private LocalDate data;
	private FormaPagamento formaPagamento;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
	
	
	
}
