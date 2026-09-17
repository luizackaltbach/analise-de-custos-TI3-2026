package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProdutosParaVenda;

public class ProdutosParaVendaDAO {

    private final DataSource dataSource;

    public ProdutosParaVendaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ProdutosParaVenda obj) throws SQLException {
        String sql = "INSERT INTO produtos_para_venda (codigo, nome, unidade, quantidade, custo_geral, custo_unitario) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getUnidade());
            ps.setDouble(4, obj.getQuantidade());
            ps.setDouble(5, obj.getCustoGeral());
            ps.setDouble(6, obj.getCustoUnitario());
            ps.executeUpdate();
        }
    }

    public void alterar(ProdutosParaVenda obj) throws SQLException {
        String sql = "UPDATE produtos_para_venda SET codigo = ?, nome = ?, unidade = ?, quantidade = ?, custo_geral = ?, custo_unitario = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getUnidade());
            ps.setDouble(4, obj.getQuantidade());
            ps.setDouble(5, obj.getCustoGeral());
            ps.setDouble(6, obj.getCustoUnitario());
            ps.setInt(7, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produtos_para_venda WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ProdutosParaVenda buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos_para_venda WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<ProdutosParaVenda> listarTodos() throws SQLException {
        List<ProdutosParaVenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos_para_venda ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private ProdutosParaVenda montar(ResultSet rs) throws SQLException {
        ProdutosParaVenda obj = new ProdutosParaVenda();
        obj.setId(rs.getInt("id"));
        obj.setCodigo(rs.getString("codigo"));
        obj.setNome(rs.getString("nome"));
        obj.setUnidade(rs.getString("unidade"));
        obj.setQuantidade(rs.getDouble("quantidade"));
        obj.setCustoGeral(rs.getDouble("custo_geral"));
        obj.setCustoUnitario(rs.getDouble("custo_unitario"));
        return obj;
    }
}
