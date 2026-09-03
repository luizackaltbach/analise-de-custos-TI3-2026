package model;

/**
 * Entidade da tabela centro_custo.
 */
public class CentroCusto {

    private int id;
    private String nome;
    private int horasEfetivas;

    public CentroCusto() {
    }

    public CentroCusto(String nome, int horasEfetivas) {
        this.nome = nome;
        this.horasEfetivas = horasEfetivas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasEfetivas() {
        return horasEfetivas;
    }

    public void setHorasEfetivas(int horasEfetivas) {
        this.horasEfetivas = horasEfetivas;
    }

    @Override
    public String toString() {
        return "CentroCusto{" + "id=" + id + ", nome=" + nome + ", horasEfetivas=" + horasEfetivas + "}";
    }
}
