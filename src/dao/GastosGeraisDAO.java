package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GastosGerais;

/**
 * Acesso a dados da tabela gastos_gerais.
 */
public class GastosGeraisDAO {

    private final DataSource dataSource;

    public GastosGeraisDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(GastosGerais obj) throws SQLException {
        String sql = "INSERT INTO gastos_gerais (nome_conta) VALUES (?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getNomeConta());
            ps.executeUpdate();
        }
    }

    public void alterar(GastosGerais obj) throws SQLException {
        String sql = "UPDATE gastos_gerais SET nome_conta = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getNomeConta());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM gastos_gerais WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public GastosGerais buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM gastos_gerais WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<GastosGerais> listarTodos() throws SQLException {
        List<GastosGerais> lista = new ArrayList<>();
        String sql = "SELECT * FROM gastos_gerais ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private GastosGerais montar(ResultSet rs) throws SQLException {
        GastosGerais obj = new GastosGerais();
        obj.setId(rs.getInt("id"));
        obj.setNomeConta(rs.getString("nome_conta"));
        return obj;
    }
}
