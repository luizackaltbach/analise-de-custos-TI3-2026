package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela composicao_materia_prima.
 */
public class ComposicaoMateriaPrima {

    private int id;
    private int idProdutoVenda;
    private int idMateriaPrima;
    private BigDecimal quantidade;
    private BigDecimal custoReposicao;
    private BigDecimal total;  // calculada pelo banco

    public ComposicaoMateriaPrima() {
    }

    public ComposicaoMateriaPrima(int idProdutoVenda, int idMateriaPrima, BigDecimal quantidade, BigDecimal custoReposicao) {
        this.idProdutoVenda = idProdutoVenda;
        this.idMateriaPrima = idMateriaPrima;
        this.quantidade = quantidade;
        this.custoReposicao = custoReposicao;
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

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getCustoReposicao() {
        return custoReposicao;
    }

    public void setCustoReposicao(BigDecimal custoReposicao) {
        this.custoReposicao = custoReposicao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ComposicaoMateriaPrima{" + "id=" + id + ", idProdutoVenda=" + idProdutoVenda + ", idMateriaPrima=" + idMateriaPrima + ", quantidade=" + quantidade + ", custoReposicao=" + custoReposicao + ", total=" + total + "}";
    }
}
