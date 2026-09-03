package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela custo_por_hora.
 */
public class CustoPorHora {

    private int id;
    private int idCentroCusto;
    private int idGastoGeral;
    private BigDecimal valor;
    private BigDecimal custoMinuto;

    public CustoPorHora() {
    }

    public CustoPorHora(int idCentroCusto, int idGastoGeral, BigDecimal valor, BigDecimal custoMinuto) {
        this.idCentroCusto = idCentroCusto;
        this.idGastoGeral = idGastoGeral;
        this.valor = valor;
        this.custoMinuto = custoMinuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(int idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public int getIdGastoGeral() {
        return idGastoGeral;
    }

    public void setIdGastoGeral(int idGastoGeral) {
        this.idGastoGeral = idGastoGeral;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getCustoMinuto() {
        return custoMinuto;
    }

    public void setCustoMinuto(BigDecimal custoMinuto) {
        this.custoMinuto = custoMinuto;
    }

    @Override
    public String toString() {
        return "CustoPorHora{" + "id=" + id + ", idCentroCusto=" + idCentroCusto + ", idGastoGeral=" + idGastoGeral + ", valor=" + valor + ", custoMinuto=" + custoMinuto + "}";
    }
}
