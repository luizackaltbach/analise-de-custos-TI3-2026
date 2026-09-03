package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PrevisaoCustosFixos;

/**
 * Acesso a dados da tabela previsao_custos_fixos.
 */
public class PrevisaoCustosFixosDAO {

    private final DataSource dataSource;

    public PrevisaoCustosFixosDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(PrevisaoCustosFixos obj) throws SQLException {
        String sql = "INSERT INTO previsao_custos_fixos (competencia, sequencia, id_gasto_geral, id_centro_custo, valor) VALUES (?, ?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, obj.getCompetencia());
            ps.setInt(2, obj.getSequencia());
            ps.setInt(3, obj.getIdGastoGeral());
            ps.setInt(4, obj.getIdCentroCusto());
            ps.setBigDecimal(5, obj.getValor());
            ps.executeUpdate();
        }
    }

    public void alterar(PrevisaoCustosFixos obj) throws SQLException {
        String sql = "UPDATE previsao_custos_fixos SET competencia = ?, sequencia = ?, id_gasto_geral = ?, id_centro_custo = ?, valor = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, obj.getCompetencia());
            ps.setInt(2, obj.getSequencia());
            ps.setInt(3, obj.getIdGastoGeral());
            ps.setInt(4, obj.getIdCentroCusto());
            ps.setBigDecimal(5, obj.getValor());
            ps.setInt(6, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM previsao_custos_fixos WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public PrevisaoCustosFixos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM previsao_custos_fixos WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<PrevisaoCustosFixos> listarTodos() throws SQLException {
        List<PrevisaoCustosFixos> lista = new ArrayList<>();
        String sql = "SELECT * FROM previsao_custos_fixos ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private PrevisaoCustosFixos montar(ResultSet rs) throws SQLException {
        PrevisaoCustosFixos obj = new PrevisaoCustosFixos();
        obj.setId(rs.getInt("id"));
        obj.setCompetencia(rs.getObject("competencia", java.time.LocalDate.class));
        obj.setSequencia(rs.getInt("sequencia"));
        obj.setIdGastoGeral(rs.getInt("id_gasto_geral"));
        obj.setIdCentroCusto(rs.getInt("id_centro_custo"));
        obj.setValor(rs.getBigDecimal("valor"));
        return obj;
    }
}
