package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CustoPorHora;

/**
 * Acesso a dados da tabela custo_por_hora.
 */
public class CustoPorHoraDAO {

    private final DataSource dataSource;

    public CustoPorHoraDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(CustoPorHora obj) throws SQLException {
        String sql = "INSERT INTO custo_por_hora (id_centro_custo, id_gasto_geral, valor, custo_minuto) VALUES (?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdCentroCusto());
            ps.setInt(2, obj.getIdGastoGeral());
            ps.setBigDecimal(3, obj.getValor());
            ps.setBigDecimal(4, obj.getCustoMinuto());
            ps.executeUpdate();
        }
    }

    public void alterar(CustoPorHora obj) throws SQLException {
        String sql = "UPDATE custo_por_hora SET id_centro_custo = ?, id_gasto_geral = ?, valor = ?, custo_minuto = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdCentroCusto());
            ps.setInt(2, obj.getIdGastoGeral());
            ps.setBigDecimal(3, obj.getValor());
            ps.setBigDecimal(4, obj.getCustoMinuto());
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM custo_por_hora WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public CustoPorHora buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM custo_por_hora WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<CustoPorHora> listarTodos() throws SQLException {
        List<CustoPorHora> lista = new ArrayList<>();
        String sql = "SELECT * FROM custo_por_hora ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private CustoPorHora montar(ResultSet rs) throws SQLException {
        CustoPorHora obj = new CustoPorHora();
        obj.setId(rs.getInt("id"));
        obj.setIdCentroCusto(rs.getInt("id_centro_custo"));
        obj.setIdGastoGeral(rs.getInt("id_gasto_geral"));
        obj.setValor(rs.getBigDecimal("valor"));
        obj.setCustoMinuto(rs.getBigDecimal("custo_minuto"));
        return obj;
    }
}
