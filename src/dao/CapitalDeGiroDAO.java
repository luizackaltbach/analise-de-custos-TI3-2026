package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CapitalDeGiro;

/**
 * Acesso a dados da tabela capital_de_giro.
 */
public class CapitalDeGiroDAO {

    private final DataSource dataSource;

    public CapitalDeGiroDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(CapitalDeGiro obj) throws SQLException {
        String sql = "INSERT INTO capital_de_giro (situacao, prazo_medio_dias, investimento_variavel, periodo) VALUES (?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getSituacao());
            ps.setDouble(2, obj.getPrazoMedioDias());
            ps.setDouble(3, obj.getInvestimentoVariavel());
            ps.setDouble(4, obj.getPeriodo());
            ps.executeUpdate();
        }
    }

    public void alterar(CapitalDeGiro obj) throws SQLException {
        String sql = "UPDATE capital_de_giro SET situacao = ?, prazo_medio_dias = ?, investimento_variavel = ?, periodo = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getSituacao());
            ps.setDouble(2, obj.getPrazoMedioDias());
            ps.setDouble(3, obj.getInvestimentoVariavel());
            ps.setDouble(4, obj.getPeriodo());
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM capital_de_giro WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public CapitalDeGiro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM capital_de_giro WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<CapitalDeGiro> listarTodos() throws SQLException {
        List<CapitalDeGiro> lista = new ArrayList<>();
        String sql = "SELECT * FROM capital_de_giro ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private CapitalDeGiro montar(ResultSet rs) throws SQLException {
        CapitalDeGiro obj = new CapitalDeGiro();
        obj.setId(rs.getInt("id"));
        obj.setSituacao(rs.getString("situacao"));
        obj.setPrazoMedioDias(rs.getDouble("prazo_medio_dias"));
        obj.setInvestimentoVariavel(rs.getDouble("investimento_variavel"));
        obj.setPeriodo(rs.getDouble("periodo"));
        return obj;
    }
}
