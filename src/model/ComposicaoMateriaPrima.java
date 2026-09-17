package model;


/**
 * Entidade da tabela composicao_materia_prima.
 */
public class ComposicaoMateriaPrima {

    private int id;
    private int idProdutoVenda;
    private int idMateriaPrima;
    private double quantidade;
    private double custoReposicao;
    private double total;  // calculada pelo banco

    public ComposicaoMateriaPrima() {
    }

    public ComposicaoMateriaPrima(int idProdutoVenda, int idMateriaPrima, double quantidade, double custoReposicao) {
        this.idProdutoVenda = idProdutoVenda;
        this.idMateriaPrima = idMateriaPrima;
        this.quantidade = quantidade;
        this.custoReposicao = custoReposicao;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ComposicaoMateriaPrima{" + "id=" + id + ", idProdutoVenda=" + idProdutoVenda + ", idMateriaPrima=" + idMateriaPrima + ", quantidade=" + quantidade + ", custoReposicao=" + custoReposicao + ", total=" + total + "}";
    }
}
