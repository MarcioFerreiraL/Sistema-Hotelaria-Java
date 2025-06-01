package com.projetoprogramacaoii.model.financeiro;

import java.time.LocalDate;

public class Lancamento {
	private TipoLancamento tipoLancamento;
	private String descricao;
	private double valor;
	private LocalDate data;

	public Lancamento(double valor, TipoLancamento tipoLancamento, String descricao, LocalDate data) {
		this.valor = valor;
		this.tipoLancamento = tipoLancamento;
		this.descricao = descricao;
		this.data = data;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
