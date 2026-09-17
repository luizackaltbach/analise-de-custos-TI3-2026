package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DespesasComVendas;

/**
 * Acesso a dados da tabela despesas_com_vendas.
 */
public class DespesasComVendasDAO {

    private final DataSource dataSource;

    public DespesasComVendasDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(DespesasComVendas obj) throws SQLException {
        String sql = "INSERT INTO despesas_com_vendas (nome_conta, percentual) VALUES (?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getNomeConta());
            ps.setDouble(2, obj.getPercentual());
            ps.executeUpdate();
        }
    }

    public void alterar(DespesasComVendas obj) throws SQLException {
        String sql = "UPDATE despesas_com_vendas SET nome_conta = ?, percentual = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getNomeConta());
            ps.setDouble(2, obj.getPercentual());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM despesas_com_vendas WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public DespesasComVendas buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM despesas_com_vendas WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<DespesasComVendas> listarTodos() throws SQLException {
        List<DespesasComVendas> lista = new ArrayList<>();
        String sql = "SELECT * FROM despesas_com_vendas ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private DespesasComVendas montar(ResultSet rs) throws SQLException {
        DespesasComVendas obj = new DespesasComVendas();
        obj.setId(rs.getInt("id"));
        obj.setNomeConta(rs.getString("nome_conta"));
        obj.setPercentual(rs.getDouble("percentual"));
        return obj;
    }
}
