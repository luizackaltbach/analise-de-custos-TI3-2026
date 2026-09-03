package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela composicao_materia_prima.
 */
public class ComposicaoMateriaPrima {

    private int id;
    private int idProdutoVenda;
    private int idProdutoPrimario;
    private BigDecimal quantidade;
    private BigDecimal custoReposicao;
    private BigDecimal total;  // calculada pelo banco

    public ComposicaoMateriaPrima() {
    }

    public ComposicaoMateriaPrima(int idProdutoVenda, int idProdutoPrimario, BigDecimal quantidade, BigDecimal custoReposicao) {
        this.idProdutoVenda = idProdutoVenda;
        this.idProdutoPrimario = idProdutoPrimario;
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

    public int getIdProdutoPrimario() {
        return idProdutoPrimario;
    }

    public void setIdProdutoPrimario(int idProdutoPrimario) {
        this.idProdutoPrimario = idProdutoPrimario;
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
        return "ComposicaoMateriaPrima{" + "id=" + id + ", idProdutoVenda=" + idProdutoVenda + ", idProdutoPrimario=" + idProdutoPrimario + ", quantidade=" + quantidade + ", custoReposicao=" + custoReposicao + ", total=" + total + "}";
    }
}
