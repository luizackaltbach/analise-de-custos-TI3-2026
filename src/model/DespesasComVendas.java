package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela despesas_com_vendas.
 */
public class DespesasComVendas {

    private int id;
    private String nomeConta;
    private BigDecimal percentual;

    public DespesasComVendas() {
    }

    public DespesasComVendas(String nomeConta, BigDecimal percentual) {
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

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    @Override
    public String toString() {
        return "DespesasComVendas{" + "id=" + id + ", nomeConta=" + nomeConta + ", percentual=" + percentual + "}";
    }
}
