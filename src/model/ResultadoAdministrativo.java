package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade da tabela resultado_administrativo.
 */
public class ResultadoAdministrativo {

    private int id;
    private LocalDateTime dataCalculo;
    private int idProdutoVenda;
    private String codigoProduto;
    private String nomeProduto;
    private String unidade;
    private BigDecimal total;
    private BigDecimal investimentoFixo;
    private String tipoInvestimento;
    private BigDecimal investimentoVariavel;
    private BigDecimal totalReposicao;
    private BigDecimal totalCustoFixo;
    private BigDecimal percentualFixo;
    private BigDecimal taxaRetornoCapital;
    private BigDecimal receitaLiquida;
    private String tipoDespesa;
    private BigDecimal despesaVenda;
    private BigDecimal receitaBruta;
    private BigDecimal precoFinalUnitario;
    private BigDecimal precoVendaDesconto;
    private BigDecimal descontoProgramado;
    private BigDecimal aumento;
    private BigDecimal pontoEquilibrioVariavel;
    private BigDecimal pontoEquilibrioFixo;
    private BigDecimal margemLucro;

    public ResultadoAdministrativo() {
    }

    public ResultadoAdministrativo(LocalDateTime dataCalculo, int idProdutoVenda, String codigoProduto, String nomeProduto, String unidade, BigDecimal total, BigDecimal investimentoFixo, String tipoInvestimento, BigDecimal investimentoVariavel, BigDecimal totalReposicao, BigDecimal totalCustoFixo, BigDecimal percentualFixo, BigDecimal taxaRetornoCapital, BigDecimal receitaLiquida, String tipoDespesa, BigDecimal despesaVenda, BigDecimal receitaBruta, BigDecimal precoFinalUnitario, BigDecimal precoVendaDesconto, BigDecimal descontoProgramado, BigDecimal aumento, BigDecimal pontoEquilibrioVariavel, BigDecimal pontoEquilibrioFixo, BigDecimal margemLucro) {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getInvestimentoFixo() {
        return investimentoFixo;
    }

    public void setInvestimentoFixo(BigDecimal investimentoFixo) {
        this.investimentoFixo = investimentoFixo;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public BigDecimal getInvestimentoVariavel() {
        return investimentoVariavel;
    }

    public void setInvestimentoVariavel(BigDecimal investimentoVariavel) {
        this.investimentoVariavel = investimentoVariavel;
    }

    public BigDecimal getTotalReposicao() {
        return totalReposicao;
    }

    public void setTotalReposicao(BigDecimal totalReposicao) {
        this.totalReposicao = totalReposicao;
    }

    public BigDecimal getTotalCustoFixo() {
        return totalCustoFixo;
    }

    public void setTotalCustoFixo(BigDecimal totalCustoFixo) {
        this.totalCustoFixo = totalCustoFixo;
    }

    public BigDecimal getPercentualFixo() {
        return percentualFixo;
    }

    public void setPercentualFixo(BigDecimal percentualFixo) {
        this.percentualFixo = percentualFixo;
    }

    public BigDecimal getTaxaRetornoCapital() {
        return taxaRetornoCapital;
    }

    public void setTaxaRetornoCapital(BigDecimal taxaRetornoCapital) {
        this.taxaRetornoCapital = taxaRetornoCapital;
    }

    public BigDecimal getReceitaLiquida() {
        return receitaLiquida;
    }

    public void setReceitaLiquida(BigDecimal receitaLiquida) {
        this.receitaLiquida = receitaLiquida;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public BigDecimal getDespesaVenda() {
        return despesaVenda;
    }

    public void setDespesaVenda(BigDecimal despesaVenda) {
        this.despesaVenda = despesaVenda;
    }

    public BigDecimal getReceitaBruta() {
        return receitaBruta;
    }

    public void setReceitaBruta(BigDecimal receitaBruta) {
        this.receitaBruta = receitaBruta;
    }

    public BigDecimal getPrecoFinalUnitario() {
        return precoFinalUnitario;
    }

    public void setPrecoFinalUnitario(BigDecimal precoFinalUnitario) {
        this.precoFinalUnitario = precoFinalUnitario;
    }

    public BigDecimal getPrecoVendaDesconto() {
        return precoVendaDesconto;
    }

    public void setPrecoVendaDesconto(BigDecimal precoVendaDesconto) {
        this.precoVendaDesconto = precoVendaDesconto;
    }

    public BigDecimal getDescontoProgramado() {
        return descontoProgramado;
    }

    public void setDescontoProgramado(BigDecimal descontoProgramado) {
        this.descontoProgramado = descontoProgramado;
    }

    public BigDecimal getAumento() {
        return aumento;
    }

    public void setAumento(BigDecimal aumento) {
        this.aumento = aumento;
    }

    public BigDecimal getPontoEquilibrioVariavel() {
        return pontoEquilibrioVariavel;
    }

    public void setPontoEquilibrioVariavel(BigDecimal pontoEquilibrioVariavel) {
        this.pontoEquilibrioVariavel = pontoEquilibrioVariavel;
    }

    public BigDecimal getPontoEquilibrioFixo() {
        return pontoEquilibrioFixo;
    }

    public void setPontoEquilibrioFixo(BigDecimal pontoEquilibrioFixo) {
        this.pontoEquilibrioFixo = pontoEquilibrioFixo;
    }

    public BigDecimal getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(BigDecimal margemLucro) {
        this.margemLucro = margemLucro;
    }

    @Override
    public String toString() {
        return "ResultadoAdministrativo{" + "id=" + id + ", dataCalculo=" + dataCalculo + ", idProdutoVenda=" + idProdutoVenda + ", codigoProduto=" + codigoProduto + ", nomeProduto=" + nomeProduto + ", unidade=" + unidade + ", total=" + total + ", investimentoFixo=" + investimentoFixo + ", tipoInvestimento=" + tipoInvestimento + ", investimentoVariavel=" + investimentoVariavel + ", totalReposicao=" + totalReposicao + ", totalCustoFixo=" + totalCustoFixo + ", percentualFixo=" + percentualFixo + ", taxaRetornoCapital=" + taxaRetornoCapital + ", receitaLiquida=" + receitaLiquida + ", tipoDespesa=" + tipoDespesa + ", despesaVenda=" + despesaVenda + ", receitaBruta=" + receitaBruta + ", precoFinalUnitario=" + precoFinalUnitario + ", precoVendaDesconto=" + precoVendaDesconto + ", descontoProgramado=" + descontoProgramado + ", aumento=" + aumento + ", pontoEquilibrioVariavel=" + pontoEquilibrioVariavel + ", pontoEquilibrioFixo=" + pontoEquilibrioFixo + ", margemLucro=" + margemLucro + "}";
    }
}
