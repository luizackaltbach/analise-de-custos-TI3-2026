package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ComposicaoMateriaPrima;

/**
 * Acesso a dados da tabela composicao_materia_prima.
 */
public class ComposicaoMateriaPrimaDAO {

    private final DataSource dataSource;

    public ComposicaoMateriaPrimaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ComposicaoMateriaPrima obj) throws SQLException {
        String sql = "INSERT INTO composicao_materia_prima (id_produto_venda, id_produto_primario, quantidade, custo_reposicao) VALUES (?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdProdutoVenda());
            ps.setInt(2, obj.getIdProdutoPrimario());
            ps.setBigDecimal(3, obj.getQuantidade());
            ps.setBigDecimal(4, obj.getCustoReposicao());
            ps.executeUpdate();
        }
    }

    public void alterar(ComposicaoMateriaPrima obj) throws SQLException {
        String sql = "UPDATE composicao_materia_prima SET id_produto_venda = ?, id_produto_primario = ?, quantidade = ?, custo_reposicao = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdProdutoVenda());
            ps.setInt(2, obj.getIdProdutoPrimario());
            ps.setBigDecimal(3, obj.getQuantidade());
            ps.setBigDecimal(4, obj.getCustoReposicao());
            ps.setInt(5, obj.getId());
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
        obj.setIdProdutoPrimario(rs.getInt("id_produto_primario"));
        obj.setQuantidade(rs.getBigDecimal("quantidade"));
        obj.setCustoReposicao(rs.getBigDecimal("custo_reposicao"));
        obj.setTotal(rs.getBigDecimal("total"));
        return obj;
    }
}
