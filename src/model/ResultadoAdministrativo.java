package model;

import java.time.LocalDateTime;

public class ResultadoAdministrativo {

    private int id;
    private LocalDateTime dataCalculo;
    private int idProdutoVenda;
    private String codigoProduto;
    private String nomeProduto;
    private String unidade;
    private double total;
    private double investimentoFixo;
    private String tipoInvestimento;
    private double investimentoVariavel;
    private double totalReposicao;
    private double totalCustoFixo;
    private double percentualFixo;
    private double taxaRetornoCapital;
    private double receitaLiquida;
    private String tipoDespesa;
    private double despesaVenda;
    private double receitaBruta;
    private double precoFinalUnitario;
    private double precoVendaDesconto;
    private double descontoProgramado;
    private double aumento;
    private double pontoEquilibrioVariavel;
    private double pontoEquilibrioFixo;
    private double margemLucro;

    public ResultadoAdministrativo() {
    }

    public ResultadoAdministrativo(LocalDateTime dataCalculo, int idProdutoVenda, String codigoProduto, String nomeProduto, String unidade, double total, double investimentoFixo, String tipoInvestimento, double investimentoVariavel, double totalReposicao, double totalCustoFixo, double percentualFixo, double taxaRetornoCapital, double receitaLiquida, String tipoDespesa, double despesaVenda, double receitaBruta, double precoFinalUnitario, double precoVendaDesconto, double descontoProgramado, double aumento, double pontoEquilibrioVariavel, double pontoEquilibrioFixo, double margemLucro) {
        this.dataCalculo = dataCalculo;
        this.idProdutoVenda = idProdutoVenda;
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.unidade = unidade;
        this.total = total;
        this.investimentoFixo = investimentoFixo;
        this.tipoInvestimento = tipoInvestimento;
        this.investimentoVariavel = investimentoVariavel;
        this.totalReposicao = totalReposicao;
        this.totalCustoFixo = totalCustoFixo;
        this.percentualFixo = percentualFixo;
        this.taxaRetornoCapital = taxaRetornoCapital;
        this.receitaLiquida = receitaLiquida;
        this.tipoDespesa = tipoDespesa;
        this.despesaVenda = despesaVenda;
        this.receitaBruta = receitaBruta;
        this.precoFinalUnitario = precoFinalUnitario;
        this.precoVendaDesconto = precoVendaDesconto;
        this.descontoProgramado = descontoProgramado;
        this.aumento = aumento;
        this.pontoEquilibrioVariavel = pontoEquilibrioVariavel;
        this.pontoEquilibrioFixo = pontoEquilibrioFixo;
        this.margemLucro = margemLucro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDateTime dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public int getIdProdutoVenda() {
        return idProdutoVenda;
    }

    public void setIdProdutoVenda(int idProdutoVenda) {
        this.idProdutoVenda = idProdutoVenda;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getInvestimentoFixo() {
        return investimentoFixo;
    }

    public void setInvestimentoFixo(double investimentoFixo) {
        this.investimentoFixo = investimentoFixo;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public double getInvestimentoVariavel() {
        return investimentoVariavel;
    }

    public void setInvestimentoVariavel(double investimentoVariavel) {
        this.investimentoVariavel = investimentoVariavel;
    }

    public double getTotalReposicao() {
        return totalReposicao;
    }

    public void setTotalReposicao(double totalReposicao) {
        this.totalReposicao = totalReposicao;
    }

    public double getTotalCustoFixo() {
        return totalCustoFixo;
    }

    public void setTotalCustoFixo(double totalCustoFixo) {
        this.totalCustoFixo = totalCustoFixo;
    }

    public double getPercentualFixo() {
        return percentualFixo;
    }

    public void setPercentualFixo(double percentualFixo) {
        this.percentualFixo = percentualFixo;
    }

    public double getTaxaRetornoCapital() {
        return taxaRetornoCapital;
    }

    public void setTaxaRetornoCapital(double taxaRetornoCapital) {
        this.taxaRetornoCapital = taxaRetornoCapital;
    }

    public double getReceitaLiquida() {
        return receitaLiquida;
    }

    public void setReceitaLiquida(double receitaLiquida) {
        this.receitaLiquida = receitaLiquida;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public double getDespesaVenda() {
        return despesaVenda;
    }

    public void setDespesaVenda(double despesaVenda) {
        this.despesaVenda = despesaVenda;
    }

    public double getReceitaBruta() {
        return receitaBruta;
    }

    public void setReceitaBruta(double receitaBruta) {
        this.receitaBruta = receitaBruta;
    }

    public double getPrecoFinalUnitario() {
        return precoFinalUnitario;
    }

    public void setPrecoFinalUnitario(double precoFinalUnitario) {
        this.precoFinalUnitario = precoFinalUnitario;
    }

    public double getPrecoVendaDesconto() {
        return precoVendaDesconto;
    }

    public void setPrecoVendaDesconto(double precoVendaDesconto) {
        this.precoVendaDesconto = precoVendaDesconto;
    }

    public double getDescontoProgramado() {
        return descontoProgramado;
    }

    public void setDescontoProgramado(double descontoProgramado) {
        this.descontoProgramado = descontoProgramado;
    }

    public double getAumento() {
        return aumento;
    }

    public void setAumento(double aumento) {
        this.aumento = aumento;
    }

    public double getPontoEquilibrioVariavel() {
        return pontoEquilibrioVariavel;
    }

    public void setPontoEquilibrioVariavel(double pontoEquilibrioVariavel) {
        this.pontoEquilibrioVariavel = pontoEquilibrioVariavel;
    }

    public double getPontoEquilibrioFixo() {
        return pontoEquilibrioFixo;
    }

    public void setPontoEquilibrioFixo(double pontoEquilibrioFixo) {
        this.pontoEquilibrioFixo = pontoEquilibrioFixo;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    @Override
    public String toString() {
        return "ResultadoAdministrativo{" + "id=" + id + ", dataCalculo=" + dataCalculo + ", idProdutoVenda=" + idProdutoVenda + ", codigoProduto=" + codigoProduto + ", nomeProduto=" + nomeProduto + ", unidade=" + unidade + ", total=" + total + ", investimentoFixo=" + investimentoFixo + ", tipoInvestimento=" + tipoInvestimento + ", investimentoVariavel=" + investimentoVariavel + ", totalReposicao=" + totalReposicao + ", totalCustoFixo=" + totalCustoFixo + ", percentualFixo=" + percentualFixo + ", taxaRetornoCapital=" + taxaRetornoCapital + ", receitaLiquida=" + receitaLiquida + ", tipoDespesa=" + tipoDespesa + ", despesaVenda=" + despesaVenda + ", receitaBruta=" + receitaBruta + ", precoFinalUnitario=" + precoFinalUnitario + ", precoVendaDesconto=" + precoVendaDesconto + ", descontoProgramado=" + descontoProgramado + ", aumento=" + aumento + ", pontoEquilibrioVariavel=" + pontoEquilibrioVariavel + ", pontoEquilibrioFixo=" + pontoEquilibrioFixo + ", margemLucro=" + margemLucro + "}";
    }
}
