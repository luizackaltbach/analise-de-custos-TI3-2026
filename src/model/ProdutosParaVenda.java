package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela produtos_para_venda.
 */
public class ProdutosParaVenda {

    private int id;
    private String codigo;
    private String nome;
    private String unidade;
    private BigDecimal quantidade;
    private BigDecimal custoGeral;
    private BigDecimal custoUnitario;

    public ProdutosParaVenda() {
    }

    public ProdutosParaVenda(String codigo, String nome, String unidade, BigDecimal quantidade, BigDecimal custoGeral, BigDecimal custoUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.custoGeral = custoGeral;
        this.custoUnitario = custoUnitario;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getCustoGeral() {
        return custoGeral;
    }

    public void setCustoGeral(BigDecimal custoGeral) {
        this.custoGeral = custoGeral;
    }

    public BigDecimal getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    @Override
    public String toString() {
        return "ProdutosParaVenda{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", unidade=" + unidade + ", quantidade=" + quantidade + ", custoGeral=" + custoGeral + ", custoUnitario=" + custoUnitario + "}";
    }
}
