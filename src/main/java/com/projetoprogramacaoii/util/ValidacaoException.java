package com.projetoprogramacaoii.util;

public class ValidacaoException extends Exception {
	/**
     * Construtor que recebe a mensagem de erro da validação.
     * A mensagem que descreve o erro.
     */
	private static final long serialVersionUID = 1L;

	public ValidacaoException(String message) {
        super(message);
    }
}