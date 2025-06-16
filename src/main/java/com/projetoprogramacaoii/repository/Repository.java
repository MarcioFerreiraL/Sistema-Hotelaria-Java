package com.projetoprogramacaoii.repository;

import java.util.List;

import com.projetoprogramacaoii.model.Identificacao;
// Define as operações básicas de um CRUD (Create, Read, Update, Delete)
public interface Repository<T extends Identificacao> {
	
	/**
	 * Salva uma nova entidade na fonte de dados.
	 */
	void salvar(T t);
	
	/**
	 * Busca todas as entidades da fonte de dados.
	 */
	List<T> buscarTodos();
	
	/**
	 * Busca uma entidade específica pelo seu identificador único.
	 */
	T buscarPorId(String id);
	
	/**
	 * Remove uma entidade da fonte de dados com base no seu ID.
	 */
	void remover(String id);
	
	/**
	 * Atualiza os dados de uma entidade existente.
	 */
	void atualizar(T t);
}