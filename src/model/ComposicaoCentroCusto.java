package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela composicao_centro_custo.
 */
public class ComposicaoCentroCusto {

    private int id;
    private int idProdutoVenda;
    private int idCentroCusto;
    private BigDecimal tempoMinutos;
    private BigDecimal custoMinuto;
    private BigDecimal total;  // calculada pelo banco

    public ComposicaoCentroCusto() {
    }

    public ComposicaoCentroCusto(int idProdutoVenda, int idCentroCusto, BigDecimal tempoMinutos, BigDecimal custoMinuto) {
        this.idProdutoVenda = idProdutoVenda;
        this.idCentroCusto = idCentroCusto;
        this.tempoMinutos = tempoMinutos;
        this.custoMinuto = custoMinuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProdutoVenda() {
        return idProdutoVenda;
    }

    public void setIdProdutoVenda(int idProdutoVenda) {
        this.idProdutoVenda = idProdutoVenda;
    }

    public int getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(int idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public BigDecimal getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(BigDecimal tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public BigDecimal getCustoMinuto() {
        return custoMinuto;
    }

    public void setCustoMinuto(BigDecimal custoMinuto) {
        this.custoMinuto = custoMinuto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ComposicaoCentroCusto{" + "id=" + id + ", idProdutoVenda=" + idProdutoVenda + ", idCentroCusto=" + idCentroCusto + ", tempoMinutos=" + tempoMinutos + ", custoMinuto=" + custoMinuto + ", total=" + total + "}";
    }
}
