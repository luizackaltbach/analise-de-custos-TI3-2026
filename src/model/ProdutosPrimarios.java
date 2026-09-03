package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela produtos_primarios.
 */
public class ProdutosPrimarios {

    private int id;
    private String codigo;
    private String nome;
    private String unidade;
    private BigDecimal quantidade;
    private BigDecimal custoReposicao;

    public ProdutosPrimarios() {
    }

    public ProdutosPrimarios(String codigo, String nome, String unidade, BigDecimal quantidade, BigDecimal custoReposicao) {
        this.codigo = codigo;
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.custoReposicao = custoReposicao;
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

    public BigDecimal getCustoReposicao() {
        return custoReposicao;
    }

    public void setCustoReposicao(BigDecimal custoReposicao) {
        this.custoReposicao = custoReposicao;
    }

    @Override
    public String toString() {
        return "ProdutosPrimarios{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", unidade=" + unidade + ", quantidade=" + quantidade + ", custoReposicao=" + custoReposicao + "}";
    }
}
