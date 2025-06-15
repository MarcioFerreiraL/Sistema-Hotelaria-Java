package com.projetoprogramacaoii.model.reserva;

import com.projetoprogramacaoii.model.pessoa.Cliente;

public class Reserva {
	
	private String id;
	private Cliente cliente;
	private Quarto quarto;
	private FormaPagamento formaPagamento;
	
	public Reserva(String id, Cliente cliente, Quarto quarto, FormaPagamento formaPagamento) {
		this.id = id;
		this.cliente = cliente;
		this.quarto = quarto;
		this.formaPagamento = formaPagamento;
	}

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
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}
