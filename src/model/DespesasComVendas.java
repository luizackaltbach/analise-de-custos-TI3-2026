package model;

public class DespesasComVendas {

    private int id;
    private String nomeConta;
    private double percentual;

    public DespesasComVendas() {
    }

    public DespesasComVendas(String nomeConta, double percentual) {
        this.nomeConta = nomeConta;
        this.percentual = percentual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    @Override
    public String toString() {
        return "DespesasComVendas{" + "id=" + id + ", nomeConta=" + nomeConta + ", percentual=" + percentual + "}";
    }
}
