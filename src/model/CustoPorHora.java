package model;


/**
 * Entidade da tabela custo_por_hora.
 */
public class CustoPorHora {

    private int id;
    private int idCentroCusto;
    private int idGastoGeral;
    private double valor;
    private double custoMinuto;

    public CustoPorHora() {
    }

    public CustoPorHora(int idCentroCusto, int idGastoGeral, double valor, double custoMinuto) {
        this.idCentroCusto = idCentroCusto;
        this.idGastoGeral = idGastoGeral;
        this.valor = valor;
        this.custoMinuto = custoMinuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(int idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public int getIdGastoGeral() {
        return idGastoGeral;
    }

    public void setIdGastoGeral(int idGastoGeral) {
        this.idGastoGeral = idGastoGeral;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getCustoMinuto() {
        return custoMinuto;
    }

    public void setCustoMinuto(double custoMinuto) {
        this.custoMinuto = custoMinuto;
    }

    @Override
    public String toString() {
        return "CustoPorHora{" + "id=" + id + ", idCentroCusto=" + idCentroCusto + ", idGastoGeral=" + idGastoGeral + ", valor=" + valor + ", custoMinuto=" + custoMinuto + "}";
    }
}
