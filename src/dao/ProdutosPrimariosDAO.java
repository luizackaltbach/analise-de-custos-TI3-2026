package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProdutosPrimarios;

/**
 * Acesso a dados da tabela produtos_primarios.
 */
public class ProdutosPrimariosDAO {

    private final DataSource dataSource;

    public ProdutosPrimariosDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ProdutosPrimarios obj) throws SQLException {
        String sql = "INSERT INTO produtos_primarios (codigo, nome, unidade, quantidade, custo_reposicao) VALUES (?, ?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getUnidade());
            ps.setBigDecimal(4, obj.getQuantidade());
            ps.setBigDecimal(5, obj.getCustoReposicao());
            ps.executeUpdate();
        }
    }

    public void alterar(ProdutosPrimarios obj) throws SQLException {
        String sql = "UPDATE produtos_primarios SET codigo = ?, nome = ?, unidade = ?, quantidade = ?, custo_reposicao = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getUnidade());
            ps.setBigDecimal(4, obj.getQuantidade());
            ps.setBigDecimal(5, obj.getCustoReposicao());
            ps.setInt(6, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produtos_primarios WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ProdutosPrimarios buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos_primarios WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<ProdutosPrimarios> listarTodos() throws SQLException {
        List<ProdutosPrimarios> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos_primarios ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private ProdutosPrimarios montar(ResultSet rs) throws SQLException {
        ProdutosPrimarios obj = new ProdutosPrimarios();
        obj.setId(rs.getInt("id"));
        obj.setCodigo(rs.getString("codigo"));
        obj.setNome(rs.getString("nome"));
        obj.setUnidade(rs.getString("unidade"));
        obj.setQuantidade(rs.getBigDecimal("quantidade"));
        obj.setCustoReposicao(rs.getBigDecimal("custo_reposicao"));
        return obj;
    }
}
