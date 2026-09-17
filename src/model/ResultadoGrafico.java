package model;


/**
 * Entidade da tabela resultado_grafico.
 */
public class ResultadoGrafico {

    private int id;
    private int idResultado;
    private double quantidadeVenda;
    private double receitaTotal;
    private double custoTotal;
    private double lucroPrejuizo;  // calculada pelo banco

    public ResultadoGrafico() {
    }

    public ResultadoGrafico(int idResultado, double quantidadeVenda, double receitaTotal, double custoTotal) {
        this.idResultado = idResultado;
        this.quantidadeVenda = quantidadeVenda;
        this.receitaTotal = receitaTotal;
        this.custoTotal = custoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public double getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(double quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public double getLucroPrejuizo() {
        return lucroPrejuizo;
    }

    public void setLucroPrejuizo(double lucroPrejuizo) {
        this.lucroPrejuizo = lucroPrejuizo;
    }

    @Override
    public String toString() {
        return "ResultadoGrafico{" + "id=" + id + ", idResultado=" + idResultado + ", quantidadeVenda=" + quantidadeVenda + ", receitaTotal=" + receitaTotal + ", custoTotal=" + custoTotal + ", lucroPrejuizo=" + lucroPrejuizo + "}";
    }
}
