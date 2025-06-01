package com.projetoprogramacaoii.model;

import java.util.List;

import com.projetoprogramacaoii.model.estoque.MovimentacaoEstoque;
import com.projetoprogramacaoii.model.estoque.Produto;
import com.projetoprogramacaoii.model.financeiro.Lancamento;
import com.projetoprogramacaoii.model.reserva.Quarto;
import com.projetoprogramacaoii.model.reserva.Reserva;
import com.projetoprogramacaoii.model.rh.Funcionario;

public class Hotel {

    private String nome = "Java`s hotel";
    private List<Quarto> quartos;
    private List<Produto> produtos;
    private List<Funcionario> funcionarios;
    private List<Reserva> reservas;
    private List<Lancamento> lancamentos;
    private List<MovimentacaoEstoque> movimentacoesEstoque;




}
