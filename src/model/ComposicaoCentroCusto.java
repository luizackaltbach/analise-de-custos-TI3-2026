package model;


/**
 * Entidade da tabela composicao_centro_custo.
 */
public class ComposicaoCentroCusto {

    private int id;
    private int idProdutoVenda;
    private int idCentroCusto;
    private double tempoMinutos;
    private double custoMinuto;
    private double total;  // calculada pelo banco

    public ComposicaoCentroCusto() {
    }

    public ComposicaoCentroCusto(int idProdutoVenda, int idCentroCusto, double tempoMinutos, double custoMinuto) {
        this.idProdutoVenda = idProdutoVenda;
        this.idCentroCusto = idCentroCusto;
        this.tempoMinutos = tempoMinutos;
        this.custoMinuto = custoMinuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProdutoVenda() {
        return idProdutoVenda;
    }

    public void setIdProdutoVenda(int idProdutoVenda) {
        this.idProdutoVenda = idProdutoVenda;
    }

    public int getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(int idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public double getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(double tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public double getCustoMinuto() {
        return custoMinuto;
    }

    public void setCustoMinuto(double custoMinuto) {
        this.custoMinuto = custoMinuto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ComposicaoCentroCusto{" + "id=" + id + ", idProdutoVenda=" + idProdutoVenda + ", idCentroCusto=" + idCentroCusto + ", tempoMinutos=" + tempoMinutos + ", custoMinuto=" + custoMinuto + ", total=" + total + "}";
    }
}
