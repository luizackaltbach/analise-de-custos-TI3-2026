package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade da tabela previsao_reposicao_estoque.
 */
public class PrevisaoReposicaoEstoque {

    private int id;
    private LocalDate competencia;
    private int sequencia;
    private int idMateriaPrima;
    private BigDecimal quantidade;
    private BigDecimal custoReposicao;
    private BigDecimal outrosGastos;
    private BigDecimal gastosTotais;  // calculada pelo banco

    public PrevisaoReposicaoEstoque() {
    }

    public PrevisaoReposicaoEstoque(LocalDate competencia, int sequencia, int idMateriaPrima, BigDecimal quantidade, BigDecimal custoReposicao, BigDecimal outrosGastos) {
        this.competencia = competencia;
        this.sequencia = sequencia;
        this.idMateriaPrima = idMateriaPrima;
        this.quantidade = quantidade;
        this.custoReposicao = custoReposicao;
        this.outrosGastos = outrosGastos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public int getSequencia() {
        return sequencia;
    }

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
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

    public BigDecimal getOutrosGastos() {
        return outrosGastos;
    }

    public void setOutrosGastos(BigDecimal outrosGastos) {
        this.outrosGastos = outrosGastos;
    }

    public BigDecimal getGastosTotais() {
        return gastosTotais;
    }

    public void setGastosTotais(BigDecimal gastosTotais) {
        this.gastosTotais = gastosTotais;
    }

    @Override
    public String toString() {
        return "PrevisaoReposicaoEstoque{" + "id=" + id + ", competencia=" + competencia + ", sequencia=" + sequencia + ", idMateriaPrima=" + idMateriaPrima + ", quantidade=" + quantidade + ", custoReposicao=" + custoReposicao + ", outrosGastos=" + outrosGastos + ", gastosTotais=" + gastosTotais + "}";
    }
}
