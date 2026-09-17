package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.InvestimentoFixo;

public class InvestimentoFixoDAO {

    private final DataSource dataSource;

    public InvestimentoFixoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(InvestimentoFixo obj) throws SQLException {
        String sql = "INSERT INTO investimento_fixo (tipo, valor) VALUES (?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getTipo());
            ps.setDouble(2, obj.getValor());
            ps.executeUpdate();
        }
    }

    public void alterar(InvestimentoFixo obj) throws SQLException {
        String sql = "UPDATE investimento_fixo SET tipo = ?, valor = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getTipo());
            ps.setDouble(2, obj.getValor());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM investimento_fixo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public InvestimentoFixo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM investimento_fixo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<InvestimentoFixo> listarTodos() throws SQLException {
        List<InvestimentoFixo> lista = new ArrayList<>();
        String sql = "SELECT * FROM investimento_fixo ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private InvestimentoFixo montar(ResultSet rs) throws SQLException {
        InvestimentoFixo obj = new InvestimentoFixo();
        obj.setId(rs.getInt("id"));
        obj.setTipo(rs.getString("tipo"));
        obj.setValor(rs.getDouble("valor"));
        return obj;
    }
}
