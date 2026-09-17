package model;

public class CadastroEstoque {

    private int id;
    private String codigo;
    private String produto;
    private String unidade;
    private double quantidadeAnterior;
    private double quantidade;
    private double custoAnterior;
    private double custo;
    private double custoMedio;
    private double venda;
    private double total;

    public CadastroEstoque() {
    }

    public CadastroEstoque(String codigo, String produto, String unidade, double quantidadeAnterior, double quantidade, double custoAnterior, double custo, double custoMedio, double venda, double total) {
        this.codigo = codigo;
        this.produto = produto;
        this.unidade = unidade;
        this.quantidadeAnterior = quantidadeAnterior;
        this.quantidade = quantidade;
        this.custoAnterior = custoAnterior;
        this.custo = custo;
        this.custoMedio = custoMedio;
        this.venda = venda;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getQuantidadeAnterior() {
        return quantidadeAnterior;
    }

    public void setQuantidadeAnterior(double quantidadeAnterior) {
        this.quantidadeAnterior = quantidadeAnterior;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getCustoAnterior() {
        return custoAnterior;
    }

    public void setCustoAnterior(double custoAnterior) {
        this.custoAnterior = custoAnterior;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(double custoMedio) {
        this.custoMedio = custoMedio;
    }

    public double getVenda() {
        return venda;
    }

    public void setVenda(double venda) {
        this.venda = venda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CadastroEstoque{" + "id=" + id + ", codigo=" + codigo + ", produto=" + produto + ", unidade=" + unidade + ", quantidadeAnterior=" + quantidadeAnterior + ", quantidade=" + quantidade + ", custoAnterior=" + custoAnterior + ", custo=" + custo + ", custoMedio=" + custoMedio + ", venda=" + venda + ", total=" + total + "}";
    }
}
