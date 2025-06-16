package com.projetoprogramacaoii.model.reserva;
//  Classe modelo que representa um quarto de hotel
public class Quarto {
	private int numero;
	private TipoQuarto tipoQuarto;
	private boolean ocupado;
	
	public Quarto(int numero, TipoQuarto tipo, boolean ocupado) {
		this.numero = numero;
		this.tipoQuarto = tipo;
		this.ocupado = ocupado;
	}
	
	public Quarto(int numero, TipoQuarto tipo) {
		this.numero = numero;
		this.tipoQuarto = tipo;
		this.ocupado = false;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoQuarto getTipo() {
		return tipoQuarto;
	}

	public void setTipo(TipoQuarto tipo) {
		this.tipoQuarto = tipo;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	// Define o estado do quarto como livre.
	public void liberar() {
		this.ocupado = false;
	}

	 // Define o estado do quarto como ocupado.

	public void reservar() {
		this.ocupado = true;
	}
	
	// Calcula e retorna o valor da diária com base no tipo do quarto.
	public double getValor() {
		if (tipoQuarto == TipoQuarto.SIMPLES) {
			return 100;
		} else if (tipoQuarto == TipoQuarto.LUXO) {
			return 250;
		} else if (tipoQuarto == TipoQuarto.SUITE) {
			return 500;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
	    return "Quarto{" +
	            ", numero=" + numero +
	            ", tipo=" + tipoQuarto +
	            ", valor=" + getValor() +
	            ", disponivel=" + isOcupado() +
	            '}';
	}
}
