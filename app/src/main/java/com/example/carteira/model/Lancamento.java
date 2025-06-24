package com.example.carteira.model;

import java.util.Calendar;

public class Lancamento {
    private int id;
    private String categoria;
    private String descricao;
    private String tipo;
    private Double valor;
    private int dia;
    private int mes;
    private int ano;

    public Lancamento(int id, String categoria, String descricao, String tipo, Double valor) {
        this.id = id;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;

        Calendar calendar = Calendar.getInstance();

        this.dia = calendar.get(Calendar.DAY_OF_MONTH);
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.ano = calendar.get(Calendar.YEAR);
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public String showDataFormatada() {
        return String.format(
                String.valueOf(this.dia)
                        .concat("/")
                        .concat(String.valueOf(this.mes))
                        .concat("/")
                        .concat(String.valueOf(this.ano)));
    }

    public String showValor() {
        return "R$ " .concat(String.valueOf(this.valor).replace(".", ","));
    }
}
