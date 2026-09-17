package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ComposicaoMateriaPrima;

public class ComposicaoMateriaPrimaDAO {

    private final DataSource dataSource;

    public ComposicaoMateriaPrimaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ComposicaoMateriaPrima obj) throws SQLException {
        String sql = "INSERT INTO composicao_materia_prima (id_produto_venda, id_materia_prima, quantidade, custo_reposicao, total) VALUES (?, ?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdProdutoVenda());
            ps.setInt(2, obj.getIdMateriaPrima());
            ps.setDouble(3, obj.getQuantidade());
            ps.setDouble(4, obj.getCustoReposicao());
            ps.setDouble(5, obj.getTotal());
            ps.executeUpdate();
        }
    }

    public void alterar(ComposicaoMateriaPrima obj) throws SQLException {
        String sql = "UPDATE composicao_materia_prima SET id_produto_venda = ?, id_materia_prima = ?, quantidade = ?, custo_reposicao = ?, total = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdProdutoVenda());
            ps.setInt(2, obj.getIdMateriaPrima());
            ps.setDouble(3, obj.getQuantidade());
            ps.setDouble(4, obj.getCustoReposicao());
            ps.setDouble(5, obj.getTotal());
            ps.setInt(6, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM composicao_materia_prima WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ComposicaoMateriaPrima buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM composicao_materia_prima WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<ComposicaoMateriaPrima> listarTodos() throws SQLException {
        List<ComposicaoMateriaPrima> lista = new ArrayList<>();
        String sql = "SELECT * FROM composicao_materia_prima ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private ComposicaoMateriaPrima montar(ResultSet rs) throws SQLException {
        ComposicaoMateriaPrima obj = new ComposicaoMateriaPrima();
        obj.setId(rs.getInt("id"));
        obj.setIdProdutoVenda(rs.getInt("id_produto_venda"));
        obj.setIdMateriaPrima(rs.getInt("id_materia_prima"));
        obj.setQuantidade(rs.getDouble("quantidade"));
        obj.setCustoReposicao(rs.getDouble("custo_reposicao"));
        obj.setTotal(rs.getDouble("total"));
        return obj;
    }
}
