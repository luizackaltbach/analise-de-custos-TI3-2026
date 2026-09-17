package model;

import java.time.LocalDate;

/**
 * Entidade da tabela previsao_custos_fixos.
 */
public class PrevisaoCustosFixos {

    private int id;
    private LocalDate competencia;
    private int sequencia;
    private int idGastoGeral;
    private int idCentroCusto;
    private double valor;

    public PrevisaoCustosFixos() {
    }

    public PrevisaoCustosFixos(LocalDate competencia, int sequencia, int idGastoGeral, int idCentroCusto, double valor) {
        this.competencia = competencia;
        this.sequencia = sequencia;
        this.idGastoGeral = idGastoGeral;
        this.idCentroCusto = idCentroCusto;
        this.valor = valor;
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

    public int getIdGastoGeral() {
        return idGastoGeral;
    }

    public void setIdGastoGeral(int idGastoGeral) {
        this.idGastoGeral = idGastoGeral;
    }

    public int getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(int idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "PrevisaoCustosFixos{" + "id=" + id + ", competencia=" + competencia + ", sequencia=" + sequencia + ", idGastoGeral=" + idGastoGeral + ", idCentroCusto=" + idCentroCusto + ", valor=" + valor + "}";
    }
}
