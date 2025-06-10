package com.projetoprogramacaoii.model.reserva;

import java.time.LocalDate;
import com.projetoprogramacaoii.model.pessoa.Cliente;

public class Reserva {
	
	private String id;
	private Cliente cliente;
	private Quarto quarto;
	private LocalDate data;
	private FormaPagamento formaPagamento;
	
	public Reserva(String id, Cliente cliente, Quarto quarto, LocalDate data, FormaPagamento formaPagamento) {
		this.id = id;
		this.cliente = cliente;
		this.quarto = quarto;
		this.data = data;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}
