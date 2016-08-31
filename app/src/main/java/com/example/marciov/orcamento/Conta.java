package com.example.marciov.orcamento;

/**
 * Created by MarcioV on 11/08/2016.
 */
public class Conta {

    private int _id;
    private String descricao;
    private String tipo;
    private Double valor;
    private Double saldo;
    private String quitado;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getQuitado() {
        return quitado;
    }

    public void setQuitado(String quitado) {
        this.quitado = quitado;
    }
}
