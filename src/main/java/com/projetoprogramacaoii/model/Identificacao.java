package com.projetoprogramacaoii.model;

public interface Identificacao {
	
	public default String getId(){
		return null;
	}
	
	public default String getIdentificacao(){
		return String.format("Sem identificacao");
	}
}
