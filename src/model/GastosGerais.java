package model;

/**
 * Entidade da tabela gastos_gerais.
 */
public class GastosGerais {

    private int id;
    private String nomeConta;

    public GastosGerais() {
    }

    public GastosGerais(String nomeConta) {
        this.nomeConta = nomeConta;
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

    @Override
    public String toString() {
        return "GastosGerais{" + "id=" + id + ", nomeConta=" + nomeConta + "}";
    }
}
