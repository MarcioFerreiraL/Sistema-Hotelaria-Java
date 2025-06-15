package com.projetoprogramacaoii.model.financeiro;

public class Lancamento {
	private TipoLancamento tipoLancamento;
	private String descricao;
	private double valor;

	public Lancamento(double valor, TipoLancamento tipoLancamento, String descricao) {
		this.valor = valor;
		this.tipoLancamento = tipoLancamento;
		this.descricao = descricao;
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
