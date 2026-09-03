package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela resultado_grafico.
 */
public class ResultadoGrafico {

    private int id;
    private int idResultado;
    private BigDecimal quantidadeVenda;
    private BigDecimal receitaTotal;
    private BigDecimal custoTotal;
    private BigDecimal lucroPrejuizo;  // calculada pelo banco

    public ResultadoGrafico() {
    }

    public ResultadoGrafico(int idResultado, BigDecimal quantidadeVenda, BigDecimal receitaTotal, BigDecimal custoTotal) {
        this.idResultado = idResultado;
        this.quantidadeVenda = quantidadeVenda;
        this.receitaTotal = receitaTotal;
        this.custoTotal = custoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public BigDecimal getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(BigDecimal quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public BigDecimal getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(BigDecimal receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public BigDecimal getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(BigDecimal custoTotal) {
        this.custoTotal = custoTotal;
    }

    public BigDecimal getLucroPrejuizo() {
        return lucroPrejuizo;
    }

    public void setLucroPrejuizo(BigDecimal lucroPrejuizo) {
        this.lucroPrejuizo = lucroPrejuizo;
    }

    @Override
    public String toString() {
        return "ResultadoGrafico{" + "id=" + id + ", idResultado=" + idResultado + ", quantidadeVenda=" + quantidadeVenda + ", receitaTotal=" + receitaTotal + ", custoTotal=" + custoTotal + ", lucroPrejuizo=" + lucroPrejuizo + "}";
    }
}
