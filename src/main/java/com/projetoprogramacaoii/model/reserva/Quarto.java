package com.projetoprogramacaoii.model.reserva;

public class Quarto {
	private static int numero;
	private TipoQuarto tipoQuarto;
	private boolean ocupado;
	
	public Quarto(int numero, TipoQuarto tipo, boolean ocupado) {
		this.numero = numero;
		this.tipoQuarto = tipo;
		this.ocupado = ocupado;
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
	
	public void liberar() {
		this.ocupado = false;
	}
	
	public void reservar() {
		this.ocupado = true;
	}
	
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
}
