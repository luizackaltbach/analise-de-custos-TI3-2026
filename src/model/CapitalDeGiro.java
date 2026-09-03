package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela capital_de_giro.
 */
public class CapitalDeGiro {

    private int id;
    private String situacao;
    private BigDecimal prazoMedioDias;
    private BigDecimal investimentoVariavel;
    private BigDecimal periodo;

    public CapitalDeGiro() {
    }

    public CapitalDeGiro(String situacao, BigDecimal prazoMedioDias, BigDecimal investimentoVariavel, BigDecimal periodo) {
        this.situacao = situacao;
        this.prazoMedioDias = prazoMedioDias;
        this.investimentoVariavel = investimentoVariavel;
        this.periodo = periodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getPrazoMedioDias() {
        return prazoMedioDias;
    }

    public void setPrazoMedioDias(BigDecimal prazoMedioDias) {
        this.prazoMedioDias = prazoMedioDias;
    }

    public BigDecimal getInvestimentoVariavel() {
        return investimentoVariavel;
    }

    public void setInvestimentoVariavel(BigDecimal investimentoVariavel) {
        this.investimentoVariavel = investimentoVariavel;
    }

    public BigDecimal getPeriodo() {
        return periodo;
    }

    public void setPeriodo(BigDecimal periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "CapitalDeGiro{" + "id=" + id + ", situacao=" + situacao + ", prazoMedioDias=" + prazoMedioDias + ", investimentoVariavel=" + investimentoVariavel + ", periodo=" + periodo + "}";
    }
}
