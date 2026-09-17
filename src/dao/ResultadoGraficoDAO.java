package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ResultadoGrafico;

public class ResultadoGraficoDAO {

    private final DataSource dataSource;

    public ResultadoGraficoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ResultadoGrafico obj) throws SQLException {
        String sql = "INSERT INTO resultado_grafico (id_resultado, quantidade_venda, receita_total, custo_total) VALUES (?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdResultado());
            ps.setDouble(2, obj.getQuantidadeVenda());
            ps.setDouble(3, obj.getReceitaTotal());
            ps.setDouble(4, obj.getCustoTotal());
            ps.executeUpdate();
        }
    }

    public void alterar(ResultadoGrafico obj) throws SQLException {
        String sql = "UPDATE resultado_grafico SET id_resultado = ?, quantidade_venda = ?, receita_total = ?, custo_total = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdResultado());
            ps.setDouble(2, obj.getQuantidadeVenda());
            ps.setDouble(3, obj.getReceitaTotal());
            ps.setDouble(4, obj.getCustoTotal());
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM resultado_grafico WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ResultadoGrafico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM resultado_grafico WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<ResultadoGrafico> listarTodos() throws SQLException {
        List<ResultadoGrafico> lista = new ArrayList<>();
        String sql = "SELECT * FROM resultado_grafico ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private ResultadoGrafico montar(ResultSet rs) throws SQLException {
        ResultadoGrafico obj = new ResultadoGrafico();
        obj.setId(rs.getInt("id"));
        obj.setIdResultado(rs.getInt("id_resultado"));
        obj.setQuantidadeVenda(rs.getDouble("quantidade_venda"));
        obj.setReceitaTotal(rs.getDouble("receita_total"));
        obj.setCustoTotal(rs.getDouble("custo_total"));
        obj.setLucroPrejuizo(rs.getDouble("lucro_prejuizo"));
        return obj;
    }
}
