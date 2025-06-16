package com.projetoprogramacaoii.model.rh;

import com.projetoprogramacaoii.model.Identificacao;
// Classe que representa um Funcionário do hotel.
public class Funcionario implements Identificacao{
	
	private String nome;
	private String cpf;
	private String cargo;
	private double salario;
	private TipoContrato tipoContrato;
	
	public Funcionario(String nome, String cpf, String cargo, double salario, TipoContrato tipoContrato) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.salario = salario;
		this.tipoContrato = tipoContrato;
	}
	
	public Funcionario(String nome, String cpf, String cargo, double salario) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.salario = salario;
		this.tipoContrato = null;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getId() {
		return String.format("Id: %s\n", cpf);
	}
	
	@Override
	public String getIdentificacao() {
		return String.format(
				"nome: %s\n" + 
				"id: %s\n"
				, nome, cpf
				);
	}
	
	@Override
	public String toString() {
	    return "Funcionario{" +
	            "cpf='" + getCpf() + '\'' +
	            ", cargo='" + cargo + '\'' +
	            ", salario=" + salario +
	            ", tipoContrato=" + tipoContrato +
	            ", " + super.toString() +
	            '}';
	}
}
