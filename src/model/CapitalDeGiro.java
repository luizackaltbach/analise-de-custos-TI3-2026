package model;


/**
 * Entidade da tabela capital_de_giro.
 */
public class CapitalDeGiro {

    private int id;
    private String situacao;
    private double prazoMedioDias;
    private double investimentoVariavel;
    private double periodo;

    public CapitalDeGiro() {
    }

    public CapitalDeGiro(String situacao, double prazoMedioDias, double investimentoVariavel, double periodo) {
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

    public double getPrazoMedioDias() {
        return prazoMedioDias;
    }

    public void setPrazoMedioDias(double prazoMedioDias) {
        this.prazoMedioDias = prazoMedioDias;
    }

    public double getInvestimentoVariavel() {
        return investimentoVariavel;
    }

    public void setInvestimentoVariavel(double investimentoVariavel) {
        this.investimentoVariavel = investimentoVariavel;
    }

    public double getPeriodo() {
        return periodo;
    }

    public void setPeriodo(double periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "CapitalDeGiro{" + "id=" + id + ", situacao=" + situacao + ", prazoMedioDias=" + prazoMedioDias + ", investimentoVariavel=" + investimentoVariavel + ", periodo=" + periodo + "}";
    }
}
