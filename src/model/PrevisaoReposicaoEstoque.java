package model;

import java.time.LocalDate;

/**
 * Entidade da tabela previsao_reposicao_estoque.
 */
public class PrevisaoReposicaoEstoque {

    private int id;
    private LocalDate competencia;
    private int sequencia;
    private int idMateriaPrima;
    private double quantidade;
    private double custoReposicao;
    private double outrosGastos;
    private double gastosTotais;  // calculada pelo banco

    public PrevisaoReposicaoEstoque() {
    }

    public PrevisaoReposicaoEstoque(LocalDate competencia, int sequencia, int idMateriaPrima, double quantidade, double custoReposicao, double outrosGastos) {
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getCustoReposicao() {
        return custoReposicao;
    }

    public void setCustoReposicao(double custoReposicao) {
        this.custoReposicao = custoReposicao;
    }

    public double getOutrosGastos() {
        return outrosGastos;
    }

    public void setOutrosGastos(double outrosGastos) {
        this.outrosGastos = outrosGastos;
    }

    public double getGastosTotais() {
        return gastosTotais;
    }

    public void setGastosTotais(double gastosTotais) {
        this.gastosTotais = gastosTotais;
    }

    @Override
    public String toString() {
        return "PrevisaoReposicaoEstoque{" + "id=" + id + ", competencia=" + competencia + ", sequencia=" + sequencia + ", idMateriaPrima=" + idMateriaPrima + ", quantidade=" + quantidade + ", custoReposicao=" + custoReposicao + ", outrosGastos=" + outrosGastos + ", gastosTotais=" + gastosTotais + "}";
    }
}
