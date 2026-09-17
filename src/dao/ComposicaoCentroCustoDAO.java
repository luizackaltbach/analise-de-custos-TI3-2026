package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ComposicaoCentroCusto;

public class ComposicaoCentroCustoDAO {

    private final DataSource dataSource;

    public ComposicaoCentroCustoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ComposicaoCentroCusto obj) throws SQLException {
        String sql = "INSERT INTO composicao_centro_custo (id_produto_venda, id_centro_custo, tempo_minutos, custo_minuto) VALUES (?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdProdutoVenda());
            ps.setInt(2, obj.getIdCentroCusto());
            ps.setDouble(3, obj.getTempoMinutos());
            ps.setDouble(4, obj.getCustoMinuto());
            ps.executeUpdate();
        }
    }

    public void alterar(ComposicaoCentroCusto obj) throws SQLException {
        String sql = "UPDATE composicao_centro_custo SET id_produto_venda = ?, id_centro_custo = ?, tempo_minutos = ?, custo_minuto = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdProdutoVenda());
            ps.setInt(2, obj.getIdCentroCusto());
            ps.setDouble(3, obj.getTempoMinutos());
            ps.setDouble(4, obj.getCustoMinuto());
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM composicao_centro_custo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ComposicaoCentroCusto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM composicao_centro_custo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<ComposicaoCentroCusto> listarTodos() throws SQLException {
        List<ComposicaoCentroCusto> lista = new ArrayList<>();
        String sql = "SELECT * FROM composicao_centro_custo ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private ComposicaoCentroCusto montar(ResultSet rs) throws SQLException {
        ComposicaoCentroCusto obj = new ComposicaoCentroCusto();
        obj.setId(rs.getInt("id"));
        obj.setIdProdutoVenda(rs.getInt("id_produto_venda"));
        obj.setIdCentroCusto(rs.getInt("id_centro_custo"));
        obj.setTempoMinutos(rs.getDouble("tempo_minutos"));
        obj.setCustoMinuto(rs.getDouble("custo_minuto"));
        obj.setTotal(rs.getDouble("total"));
        return obj;
    }
}
