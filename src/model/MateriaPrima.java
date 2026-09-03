package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela materia_prima.
 */
public class MateriaPrima {

    private int id;
    private String codigo;
    private String nome;
    private String unidade;
    private BigDecimal quantidadeEstoque;
    private BigDecimal custoReposicao;

    public MateriaPrima() {
    }

    public MateriaPrima(String codigo, String nome, String unidade, BigDecimal quantidadeEstoque, BigDecimal custoReposicao) {
        this.codigo = codigo;
        this.nome = nome;
        this.unidade = unidade;
        this.quantidadeEstoque = quantidadeEstoque;
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

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public BigDecimal getCustoReposicao() {
        return custoReposicao;
    }

    public void setCustoReposicao(BigDecimal custoReposicao) {
        this.custoReposicao = custoReposicao;
    }

    @Override
    public String toString() {
        return "MateriaPrima{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", unidade=" + unidade + ", quantidadeEstoque=" + quantidadeEstoque + ", custoReposicao=" + custoReposicao + "}";
    }
}
