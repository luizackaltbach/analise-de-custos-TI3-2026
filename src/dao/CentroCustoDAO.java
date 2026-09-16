package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CentroCusto;

/**
 * Acesso a dados da tabela centro_custo.
 */
public class CentroCustoDAO {

    private final DataSource dataSource;

    public CentroCustoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(CentroCusto obj) throws SQLException {
        String sql = "INSERT INTO centro_custo (nome, horas_efetivas) VALUES (?, ?)";
        Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getHorasEfetivas());
            ps.executeUpdate();
    }

    public void alterar(CentroCusto obj) throws SQLException {
        String sql = "UPDATE centro_custo SET nome = ?, horas_efetivas = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getHorasEfetivas());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM centro_custo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public CentroCusto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM centro_custo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<CentroCusto> listarTodos() throws SQLException {
        List<CentroCusto> lista = new ArrayList<>();
        String sql = "SELECT * FROM centro_custo ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private CentroCusto montar(ResultSet rs) throws SQLException {
        CentroCusto obj = new CentroCusto();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setHorasEfetivas(rs.getInt("horas_efetivas"));
        return obj;
    }
}
