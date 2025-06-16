package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.financeiro.Lancamento;
import com.projetoprogramacaoii.model.financeiro.TipoLancamento;
import com.projetoprogramacaoii.repository.LancamentoRepository;

import java.io.IOException;
import java.time.LocalDate;

public class LancamentoService {
    // Cria um objeto Lancamento com o tipo RECEITA.
    public static boolean criarReceita(double valor, String descricao, LocalDate data) throws IOException {
        Lancamento lancamento = new Lancamento(valor, TipoLancamento.RECEITA, descricao);
        LancamentoRepository.registrarReceita(lancamento);
        return true;
    }
    // Cria um objeto Lancamento com o tipo DESPESA.
    public static boolean criarDespesa(double valor, String descricao, LocalDate data) throws IOException {
        Lancamento lancamento = new Lancamento(valor, TipoLancamento.DESPESA, descricao);
        LancamentoRepository.registrarDespesa(lancamento);
        return true;
    }
}