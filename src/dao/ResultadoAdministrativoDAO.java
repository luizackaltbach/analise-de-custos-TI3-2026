package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ResultadoAdministrativo;

/**
 * Acesso a dados da tabela resultado_administrativo.
 */
public class ResultadoAdministrativoDAO {

    private final DataSource dataSource;

    public ResultadoAdministrativoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void inserir(ResultadoAdministrativo obj) throws SQLException {
        String sql = "INSERT INTO resultado_administrativo (data_calculo, id_produto_venda, codigo_produto, nome_produto, unidade, total, investimento_fixo, tipo_investimento, investimento_variavel, total_reposicao, total_custo_fixo, percentual_fixo, taxa_retorno_capital, receita_liquida, tipo_despesa, despesa_venda, receita_bruta, preco_final_unitario, preco_venda_desconto, desconto_programado, aumento, ponto_equilibrio_variavel, ponto_equilibrio_fixo, margem_lucro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, obj.getDataCalculo());
            ps.setInt(2, obj.getIdProdutoVenda());
            ps.setString(3, obj.getCodigoProduto());
            ps.setString(4, obj.getNomeProduto());
            ps.setString(5, obj.getUnidade());
            ps.setBigDecimal(6, obj.getTotal());
            ps.setBigDecimal(7, obj.getInvestimentoFixo());
            ps.setString(8, obj.getTipoInvestimento());
            ps.setBigDecimal(9, obj.getInvestimentoVariavel());
            ps.setBigDecimal(10, obj.getTotalReposicao());
            ps.setBigDecimal(11, obj.getTotalCustoFixo());
            ps.setBigDecimal(12, obj.getPercentualFixo());
            ps.setBigDecimal(13, obj.getTaxaRetornoCapital());
            ps.setBigDecimal(14, obj.getReceitaLiquida());
            ps.setString(15, obj.getTipoDespesa());
            ps.setBigDecimal(16, obj.getDespesaVenda());
            ps.setBigDecimal(17, obj.getReceitaBruta());
            ps.setBigDecimal(18, obj.getPrecoFinalUnitario());
            ps.setBigDecimal(19, obj.getPrecoVendaDesconto());
            ps.setBigDecimal(20, obj.getDescontoProgramado());
            ps.setBigDecimal(21, obj.getAumento());
            ps.setBigDecimal(22, obj.getPontoEquilibrioVariavel());
            ps.setBigDecimal(23, obj.getPontoEquilibrioFixo());
            ps.setBigDecimal(24, obj.getMargemLucro());
            ps.executeUpdate();
        }
    }

    public void alterar(ResultadoAdministrativo obj) throws SQLException {
        String sql = "UPDATE resultado_administrativo SET data_calculo = ?, id_produto_venda = ?, codigo_produto = ?, nome_produto = ?, unidade = ?, total = ?, investimento_fixo = ?, tipo_investimento = ?, investimento_variavel = ?, total_reposicao = ?, total_custo_fixo = ?, percentual_fixo = ?, taxa_retorno_capital = ?, receita_liquida = ?, tipo_despesa = ?, despesa_venda = ?, receita_bruta = ?, preco_final_unitario = ?, preco_venda_desconto = ?, desconto_programado = ?, aumento = ?, ponto_equilibrio_variavel = ?, ponto_equilibrio_fixo = ?, margem_lucro = ? WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, obj.getDataCalculo());
            ps.setInt(2, obj.getIdProdutoVenda());
            ps.setString(3, obj.getCodigoProduto());
            ps.setString(4, obj.getNomeProduto());
            ps.setString(5, obj.getUnidade());
            ps.setBigDecimal(6, obj.getTotal());
            ps.setBigDecimal(7, obj.getInvestimentoFixo());
            ps.setString(8, obj.getTipoInvestimento());
            ps.setBigDecimal(9, obj.getInvestimentoVariavel());
            ps.setBigDecimal(10, obj.getTotalReposicao());
            ps.setBigDecimal(11, obj.getTotalCustoFixo());
            ps.setBigDecimal(12, obj.getPercentualFixo());
            ps.setBigDecimal(13, obj.getTaxaRetornoCapital());
            ps.setBigDecimal(14, obj.getReceitaLiquida());
            ps.setString(15, obj.getTipoDespesa());
            ps.setBigDecimal(16, obj.getDespesaVenda());
            ps.setBigDecimal(17, obj.getReceitaBruta());
            ps.setBigDecimal(18, obj.getPrecoFinalUnitario());
            ps.setBigDecimal(19, obj.getPrecoVendaDesconto());
            ps.setBigDecimal(20, obj.getDescontoProgramado());
            ps.setBigDecimal(21, obj.getAumento());
            ps.setBigDecimal(22, obj.getPontoEquilibrioVariavel());
            ps.setBigDecimal(23, obj.getPontoEquilibrioFixo());
            ps.setBigDecimal(24, obj.getMargemLucro());
            ps.setInt(25, obj.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM resultado_administrativo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ResultadoAdministrativo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM resultado_administrativo WHERE id = ?";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? montar(rs) : null;
            }
        }
    }

    public List<ResultadoAdministrativo> listarTodos() throws SQLException {
        List<ResultadoAdministrativo> lista = new ArrayList<>();
        String sql = "SELECT * FROM resultado_administrativo ORDER BY id";
        Connection con = dataSource.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    private ResultadoAdministrativo montar(ResultSet rs) throws SQLException {
        ResultadoAdministrativo obj = new ResultadoAdministrativo();
        obj.setId(rs.getInt("id"));
        obj.setDataCalculo(rs.getObject("data_calculo", java.time.LocalDateTime.class));
        obj.setIdProdutoVenda(rs.getInt("id_produto_venda"));
        obj.setCodigoProduto(rs.getString("codigo_produto"));
        obj.setNomeProduto(rs.getString("nome_produto"));
        obj.setUnidade(rs.getString("unidade"));
        obj.setTotal(rs.getBigDecimal("total"));
        obj.setInvestimentoFixo(rs.getBigDecimal("investimento_fixo"));
        obj.setTipoInvestimento(rs.getString("tipo_investimento"));
        obj.setInvestimentoVariavel(rs.getBigDecimal("investimento_variavel"));
        obj.setTotalReposicao(rs.getBigDecimal("total_reposicao"));
        obj.setTotalCustoFixo(rs.getBigDecimal("total_custo_fixo"));
        obj.setPercentualFixo(rs.getBigDecimal("percentual_fixo"));
        obj.setTaxaRetornoCapital(rs.getBigDecimal("taxa_retorno_capital"));
        obj.setReceitaLiquida(rs.getBigDecimal("receita_liquida"));
        obj.setTipoDespesa(rs.getString("tipo_despesa"));
        obj.setDespesaVenda(rs.getBigDecimal("despesa_venda"));
        obj.setReceitaBruta(rs.getBigDecimal("receita_bruta"));
        obj.setPrecoFinalUnitario(rs.getBigDecimal("preco_final_unitario"));
        obj.setPrecoVendaDesconto(rs.getBigDecimal("preco_venda_desconto"));
        obj.setDescontoProgramado(rs.getBigDecimal("desconto_programado"));
        obj.setAumento(rs.getBigDecimal("aumento"));
        obj.setPontoEquilibrioVariavel(rs.getBigDecimal("ponto_equilibrio_variavel"));
        obj.setPontoEquilibrioFixo(rs.getBigDecimal("ponto_equilibrio_fixo"));
        obj.setMargemLucro(rs.getBigDecimal("margem_lucro"));
        return obj;
    }
}
