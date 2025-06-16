package com.projetoprogramacaoii.model;

public interface Identificacao {	
	/**
	 * Método padrão para obter o ID único da entidade.
	 * Por ser um método 'default', ele oferece uma implementação padrão
	 * (retornar null), que pode ser sobrescrita pelas classes que implementam a interface.
	 */
	public default String getId(){
		return null;
	}
	
	/**
	 * Método padrão para obter uma descrição textual da entidade.
	 */
	public default String getIdentificacao(){
		return String.format("Sem identificacao");
	}
}
