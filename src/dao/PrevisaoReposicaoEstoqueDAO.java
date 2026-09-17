package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PrevisaoReposicaoEstoque;

public class PrevisaoReposicaoEstoqueDAO {

    private final DataSource dataSource;

    public PrevisaoReposicaoEstoqueDAO(DataSource dataSource) {
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

    public void inserir(PrevisaoReposicaoEstoque obj) throws SQLException {
        String sql =
                "INSERT INTO previsao_reposicao_estoque "
                + "(competencia, sequencia, id_materia_prima, quantidade, custo_reposicao, outros_gastos) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, obj.getCompetencia());
            ps.setInt(2, obj.getSequencia());
            ps.setInt(3, obj.getIdMateriaPrima());
            ps.setDouble(4, obj.getQuantidade());
            ps.setDouble(5, obj.getCustoReposicao());
            ps.setDouble(6, obj.getOutrosGastos());

            ps.executeUpdate();
        }
    }

    public void alterar(PrevisaoReposicaoEstoque obj) throws SQLException {
        String sql =
                "UPDATE previsao_reposicao_estoque "
                + "SET competencia = ?, "
                + "sequencia = ?, "
                + "id_materia_prima = ?, "
                + "quantidade = ?, "
                + "custo_reposicao = ?, "
                + "outros_gastos = ? "
                + "WHERE id = ?";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, obj.getCompetencia());
            ps.setInt(2, obj.getSequencia());
            ps.setInt(3, obj.getIdMateriaPrima());
            ps.setDouble(4, obj.getQuantidade());
            ps.setDouble(5, obj.getCustoReposicao());
            ps.setDouble(6, obj.getOutrosGastos());
            ps.setInt(7, obj.getId());

            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql =
                "DELETE FROM previsao_reposicao_estoque WHERE id = ?";

        Connection con = getConexao();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public PrevisaoReposicaoEstoque buscarPorId(int id)
            throws SQLException {

        String sql =
                "SELECT * FROM previsao_reposicao_estoque WHERE id = ?";

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

    public List<PrevisaoReposicaoEstoque> listarTodos()
            throws SQLException {

        List<PrevisaoReposicaoEstoque> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM previsao_reposicao_estoque ORDER BY id";

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

    private PrevisaoReposicaoEstoque montar(ResultSet rs)
            throws SQLException {

        PrevisaoReposicaoEstoque obj =
                new PrevisaoReposicaoEstoque();

        obj.setId(
                rs.getInt("id")
        );

        obj.setCompetencia(
                rs.getObject(
                        "competencia",
                        java.time.LocalDate.class
                )
        );

        obj.setSequencia(
                rs.getInt("sequencia")
        );

        obj.setIdMateriaPrima(
                rs.getInt("id_materia_prima")
        );

        obj.setQuantidade(
                rs.getDouble("quantidade")
        );

        obj.setCustoReposicao(
                rs.getDouble("custo_reposicao")
        );

        obj.setOutrosGastos(
                rs.getDouble("outros_gastos")
        );

        obj.setGastosTotais(
                rs.getDouble("gastos_totais")
        );

        return obj;
    }
}