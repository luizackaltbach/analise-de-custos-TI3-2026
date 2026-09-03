package model;

import java.math.BigDecimal;

/**
 * Entidade da tabela investimento_fixo.
 */
public class InvestimentoFixo {

    private int id;
    private String tipo;
    private BigDecimal valor;

    public InvestimentoFixo() {
    }

    public InvestimentoFixo(String tipo, BigDecimal valor) {
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "InvestimentoFixo{" + "id=" + id + ", tipo=" + tipo + ", valor=" + valor + "}";
    }
}
