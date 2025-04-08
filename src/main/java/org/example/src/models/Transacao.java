package org.example.src.models;

import java.util.Date;

public class Transacao {
    public enum TipoTransacao {
        RECEITA,
        DESPESA
    }

    private Date data;
    private String descricao;
    private String categoria;
    private double valor;
    private TipoTransacao tipo;

    public Transacao(Date data, String descricao, String categoria, double valor, TipoTransacao tipo) {
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValor() {
        return valor;
    }

    public boolean isReceita() {
        return tipo == TipoTransacao.RECEITA;
    }

    public boolean isDespesa() {
        return tipo == TipoTransacao.DESPESA;
    }

    @Override
    public String toString() {
        String tipoStr = tipo == TipoTransacao.RECEITA ? "Receita" : "Despesa";
        return tipoStr + " | Categoria: " + categoria +
                " | Valor: R$" + valor +
                " | Descrição: " + descricao +
                " | Data: " + data;
    }
}