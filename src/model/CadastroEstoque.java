package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela cadastro_estoque.
 */
public class CadastroEstoque {

    private int id;
    private String codigo;
    private String produto;
    private String unidade;
    private BigDecimal quantidadeAnterior;
    private BigDecimal quantidade;
    private BigDecimal custoAnterior;
    private BigDecimal custo;
    private BigDecimal custoMedio;
    private BigDecimal venda;
    private BigDecimal total;

    public CadastroEstoque() {
    }

    public CadastroEstoque(String codigo, String produto, String unidade, BigDecimal quantidadeAnterior, BigDecimal quantidade, BigDecimal custoAnterior, BigDecimal custo, BigDecimal custoMedio, BigDecimal venda, BigDecimal total) {
        this.codigo = codigo;
        this.produto = produto;
        this.unidade = unidade;
        this.quantidadeAnterior = quantidadeAnterior;
        this.quantidade = quantidade;
        this.custoAnterior = custoAnterior;
        this.custo = custo;
        this.custoMedio = custoMedio;
        this.venda = venda;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidadeAnterior() {
        return quantidadeAnterior;
    }

    public void setQuantidadeAnterior(BigDecimal quantidadeAnterior) {
        this.quantidadeAnterior = quantidadeAnterior;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getCustoAnterior() {
        return custoAnterior;
    }

    public void setCustoAnterior(BigDecimal custoAnterior) {
        this.custoAnterior = custoAnterior;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public BigDecimal getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(BigDecimal custoMedio) {
        this.custoMedio = custoMedio;
    }

    public BigDecimal getVenda() {
        return venda;
    }

    public void setVenda(BigDecimal venda) {
        this.venda = venda;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CadastroEstoque{" + "id=" + id + ", codigo=" + codigo + ", produto=" + produto + ", unidade=" + unidade + ", quantidadeAnterior=" + quantidadeAnterior + ", quantidade=" + quantidade + ", custoAnterior=" + custoAnterior + ", custo=" + custo + ", custoMedio=" + custoMedio + ", venda=" + venda + ", total=" + total + "}";
    }
}
