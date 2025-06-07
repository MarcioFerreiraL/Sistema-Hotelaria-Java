package com.projetoprogramacaoii.service;

import com.projetoprogramacaoii.model.financeiro.Lancamento;
import com.projetoprogramacaoii.model.financeiro.TipoLancamento;
import com.projetoprogramacaoii.repository.LancamentoRepository;
import java.time.LocalDate;

public class FinanceiroService {
    private final LancamentoRepository lancamentoRepository;

    public FinanceiroService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public Lancamento registrarReceita(double valor, String descricao, LocalDate data) {
        Lancamento lancamento = new Lancamento(valor, TipoLancamento.RECEITA, descricao, data);
        return lancamentoRepository.salvar(lancamento);
    }

    public Lancamento registrarDespesa(double valor, String descricao, LocalDate data) {
        Lancamento lancamento = new Lancamento(valor, TipoLancamento.DESPESA, descricao, data);
        return lancamentoRepository.salvar(lancamento);
    }
}