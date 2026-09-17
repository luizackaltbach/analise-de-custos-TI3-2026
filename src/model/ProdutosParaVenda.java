package model;


/**
 * Entidade da tabela produtos_para_venda.
 */
public class ProdutosParaVenda {

    private int id;
    private String codigo;
    private String nome;
    private String unidade;
    private double quantidade;
    private double custoGeral;
    private double custoUnitario;

    public ProdutosParaVenda() {
    }

    public ProdutosParaVenda(String codigo, String nome, String unidade, double quantidade, double custoGeral, double custoUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.custoGeral = custoGeral;
        this.custoUnitario = custoUnitario;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getCustoGeral() {
        return custoGeral;
    }

    public void setCustoGeral(double custoGeral) {
        this.custoGeral = custoGeral;
    }

    public double getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(double custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    @Override
    public String toString() {
        return "ProdutosParaVenda{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", unidade=" + unidade + ", quantidade=" + quantidade + ", custoGeral=" + custoGeral + ", custoUnitario=" + custoUnitario + "}";
    }
}
