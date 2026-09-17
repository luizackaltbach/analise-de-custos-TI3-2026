package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MateriaPrima;

/**
 * Acesso a dados da tabela materia_prima.
 */
public class MateriaPrimaDAO {

    private final DataSource dataSource;

    public MateriaPrimaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConexao() throws SQLException {
        Connection con = dataSource.getConnection();

        if (con == null) {
            throw new SQLException(
                    "Não foi possível conectar ao banco de dados."
            );
        }

        return con;
    }

    public void inserir(MateriaPrima obj) throws SQLException {

        String sql =
                "INSERT INTO materia_prima "
                + "(codigo, nome, unidade, quantidade_estoque, custo_reposicao) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getUnidade());
            ps.setDouble(4, obj.getQuantidadeEstoque());
            ps.setDouble(5, obj.getCustoReposicao());

            ps.executeUpdate();
        }
    }

    public void alterar(MateriaPrima obj) throws SQLException {

        String sql =
                "UPDATE materia_prima SET "
                + "codigo = ?, "
                + "nome = ?, "
                + "unidade = ?, "
                + "quantidade_estoque = ?, "
                + "custo_reposicao = ? "
                + "WHERE id = ?";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getUnidade());
            ps.setDouble(4, obj.getQuantidadeEstoque());
            ps.setDouble(5, obj.getCustoReposicao());
            ps.setInt(6, obj.getId());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql =
                "DELETE FROM materia_prima WHERE id = ?";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public MateriaPrima buscarPorId(int id) throws SQLException {

        String sql =
                "SELECT * FROM materia_prima WHERE id = ?";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return montar(rs);
                }
            }
        }

        return null;
    }

    public List<MateriaPrima> listarTodos() throws SQLException {

        List<MateriaPrima> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM materia_prima ORDER BY id";

        Connection con = getConexao();

        try (
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(montar(rs));
            }
        }

        return lista;
    }

    private MateriaPrima montar(ResultSet rs) throws SQLException {

        MateriaPrima obj = new MateriaPrima();

        obj.setId(
                rs.getInt("id")
        );

        obj.setCodigo(
                rs.getString("codigo")
        );

        obj.setNome(
                rs.getString("nome")
        );

        obj.setUnidade(
                rs.getString("unidade")
        );

        obj.setQuantidadeEstoque(
                rs.getDouble("quantidade_estoque")
        );

        obj.setCustoReposicao(
                rs.getDouble("custo_reposicao")
        );

        return obj;
    }
}