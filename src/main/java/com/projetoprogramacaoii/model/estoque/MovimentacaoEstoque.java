package com.projetoprogramacaoii.model.estoque;

import java.time.LocalDate;
import com.projetoprogramacaoii.model.Identificacao;

public class MovimentacaoEstoque {
	private Produto produto;
	private int quantidade;
	private LocalDate data;
	private TipoMovimentacao tipoMovimentacao;
	private Identificacao responsavel;
	
	public MovimentacaoEstoque(Produto produto, int quantidade, LocalDate data, TipoMovimentacao tipoMovimentacao, Identificacao responsavel) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.data = data;
		this.tipoMovimentacao = tipoMovimentacao;
		this.responsavel = responsavel;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public Identificacao getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Identificacao responsavel) {
		this.responsavel = responsavel;
	}
	
	
	
}
