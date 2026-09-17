package model;

public class InvestimentoFixo {

    private int id;
    private String tipo;
    private double valor;

    public InvestimentoFixo() {
    }

    public InvestimentoFixo(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "InvestimentoFixo{" + "id=" + id + ", tipo=" + tipo + ", valor=" + valor + "}";
    }
}
