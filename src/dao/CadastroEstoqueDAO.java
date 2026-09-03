package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CadastroEstoque;

/**
 * Acesso a dados da tabela cadastro_estoque.
 */
public class CadastroEstoqueDAO {

    private final DataSource dataSource;

    public CadastroEstoqueDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(CadastroEstoque obj) throws SQLException {
        String sql = "INSERT INTO cadastro_estoque (codigo, produto, unidade, quantidade_anterior, quantidade, custo_anterior, custo, custo_medio, venda, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getProduto());
            ps.setString(3, obj.getUnidade());
            ps.setBigDecimal(4, obj.getQuantidadeAnterior());
            ps.setBigDecimal(5, obj.getQuantidade());
            ps.setBigDecimal(6, obj.getCustoAnterior());
            ps.setBigDecimal(7, obj.getCusto());
            ps.setBigDecimal(8, obj.getCustoMedio());
            ps.setBigDecimal(9, obj.getVenda());
            ps.setBigDecimal(10, obj.getTotal());
            ps.executeUpdate();
        }
    }

    public void alterar(CadastroEstoque obj) throws SQLException {
        String sql = "UPDATE cadastro_estoque SET codigo = ?, produto = ?, unidade = ?, quantidade_anterior = ?, quantidade = ?, custo_anterior = ?, custo = ?, custo_medio = ?, venda = ?, total = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getProduto());
            ps.setString(3, obj.getUnidade());
            ps.setBigDecimal(4, obj.getQuantidadeAnterior());
            ps.setBigDecimal(5, obj.getQuantidade());
            ps.setBigDecimal(6, obj.getCustoAnterior());
            ps.setBigDecimal(7, obj.getCusto());
            ps.setBigDecimal(8, obj.getCustoMedio());
            ps.setBigDecimal(9, obj.getVenda());
            ps.setBigDecimal(10, obj.getTotal());
            ps.setInt(11, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM cadastro_estoque WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public CadastroEstoque buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cadastro_estoque WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<CadastroEstoque> listarTodos() throws SQLException {
        List<CadastroEstoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_estoque ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private CadastroEstoque montar(ResultSet rs) throws SQLException {
        CadastroEstoque obj = new CadastroEstoque();
        obj.setId(rs.getInt("id"));
        obj.setCodigo(rs.getString("codigo"));
        obj.setProduto(rs.getString("produto"));
        obj.setUnidade(rs.getString("unidade"));
        obj.setQuantidadeAnterior(rs.getBigDecimal("quantidade_anterior"));
        obj.setQuantidade(rs.getBigDecimal("quantidade"));
        obj.setCustoAnterior(rs.getBigDecimal("custo_anterior"));
        obj.setCusto(rs.getBigDecimal("custo"));
        obj.setCustoMedio(rs.getBigDecimal("custo_medio"));
        obj.setVenda(rs.getBigDecimal("venda"));
        obj.setTotal(rs.getBigDecimal("total"));
        return obj;
    }
}
