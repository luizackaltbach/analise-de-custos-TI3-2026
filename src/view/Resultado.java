
package view;

import dao.CapitalDeGiroDAO;
import dao.ComposicaoCentroCustoDAO;
import dao.ComposicaoMateriaPrimaDAO;
import dao.DataSource;
import dao.DespesasComVendasDAO;
import dao.InvestimentoFixoDAO;
import dao.ProdutosParaVendaDAO;
import dao.ResultadoAdministrativoDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CapitalDeGiro;
import model.ComposicaoCentroCusto;
import model.ComposicaoMateriaPrima;
import model.DespesasComVendas;
import model.InvestimentoFixo;
import model.ProdutosParaVenda;
import model.ResultadoAdministrativo;

public class Resultado extends javax.swing.JInternalFrame {

    private DataSource ds = new DataSource();
    private ProdutosParaVendaDAO produtoDao = new ProdutosParaVendaDAO(ds);
    private ComposicaoMateriaPrimaDAO composicaoMateriaDao = new ComposicaoMateriaPrimaDAO(ds);
    private ComposicaoCentroCustoDAO composicaoCentroDao = new ComposicaoCentroCustoDAO(ds);
    private InvestimentoFixoDAO investimentoDao = new InvestimentoFixoDAO(ds);
    private CapitalDeGiroDAO capitalDao = new CapitalDeGiroDAO(ds);
    private DespesasComVendasDAO despesaDao = new DespesasComVendasDAO(ds);
    private ResultadoAdministrativoDAO resultadoDao = new ResultadoAdministrativoDAO(ds);

    private List<ProdutosParaVenda> produtos = new ArrayList<>();
    private List<CapitalDeGiro> capitais = new ArrayList<>();
    private List<DespesasComVendas> despesas = new ArrayList<>();

    public Resultado() {
        initComponents();
        carregarCombos();
    }

    private void carregarCombos() {
        try {
            produtos = produtoDao.listarTodos();
            capitais = capitalDao.listarTodos();
            despesas = despesaDao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os combos: " + e.getMessage());
            return;
        }

        produtoNomeTexto.removeAllItems();
        for (ProdutosParaVenda p : produtos) {
            produtoNomeTexto.addItem(p.getNome());
        }

        capitalGiroCombo.removeAllItems();
        for (CapitalDeGiro c : capitais) {
            capitalGiroCombo.addItem(c.getSituacao());
        }

        tipoDespesaCombo.removeAllItems();
        for (DespesasComVendas d : despesas) {
            tipoDespesaCombo.addItem(d.getNomeConta());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        produtoNomeTexto = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        custoVariavelUnitarioTexto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        totalReposicaoEstoqueTexto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        totalCustoFixoTexto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        taxaRetornoCapitalTexto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        capitalGiroCombo = new javax.swing.JComboBox<>();
        investimentoFixoTexto = new javax.swing.JTextField();
        investimentoVariavelTexto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        descontoProgramado = new javax.swing.JTextField();
        despesaVendaTexto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tipoDespesaCombo = new javax.swing.JComboBox<>();
        receitaLiquidaTexto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        precoValorUnitarioTexto = new javax.swing.JTextField();
        precoValorUnitario2Texto = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        receitaBrutaTexto = new javax.swing.JTextField();
        resultadoBotao = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Resultado");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("Nome do Produto");

        produtoNomeTexto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Custo Unitário Variável");

        custoVariavelUnitarioTexto.setEnabled(false);

        jLabel15.setText("Total. Custo Fixo");

        jLabel16.setText("T.R.C");

        totalReposicaoEstoqueTexto.setEnabled(false);

        jLabel17.setText("Valor do Inv. Fixo");

        totalCustoFixoTexto.setEnabled(false);

        jLabel18.setText("Cap. Giro");

        jLabel19.setText("Inv. Variável");

        capitalGiroCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        capitalGiroCombo.addActionListener(this::capitalGiroComboActionPerformed);

        investimentoFixoTexto.setEnabled(false);

        investimentoVariavelTexto.setEnabled(false);

        jLabel20.setText("Total Repo. Estoque");

        jLabel14.setText("Receita Líquida");

        despesaVendaTexto.setEnabled(false);

        jLabel21.setText("Desc. Prog.");

        jLabel22.setText("Desp V.");

        jLabel23.setText("Origem desp. Venda");

        tipoDespesaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        receitaLiquidaTexto.setEnabled(false);

        jLabel24.setText("Receita Bruta");

        jLabel25.setText("Preço V.  c/Desc.");

        precoValorUnitarioTexto.setEnabled(false);

        precoValorUnitario2Texto.setEnabled(false);

        jLabel26.setText("Preço V. s/Desc");

        receitaBrutaTexto.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(receitaLiquidaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(tipoDespesaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(despesaVendaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descontoProgramado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(receitaBrutaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(precoValorUnitarioTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(produtoNomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(custoVariavelUnitarioTexto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(investimentoFixoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(capitalGiroCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(investimentoVariavelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(totalReposicaoEstoqueTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalCustoFixoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxaRetornoCapitalTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precoValorUnitario2Texto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(investimentoVariavelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalReposicaoEstoqueTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalCustoFixoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxaRetornoCapitalTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(produtoNomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(custoVariavelUnitarioTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(investimentoFixoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(capitalGiroCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(despesaVendaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descontoProgramado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(receitaBrutaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precoValorUnitarioTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precoValorUnitario2Texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipoDespesaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(receitaLiquidaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        resultadoBotao.setText("Resultado");
        resultadoBotao.addActionListener(this::resultadoBotaoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addComponent(resultadoBotao, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultadoBotao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resultadoBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoBotaoActionPerformed
        ProdutosParaVenda produto = produtos.get(produtoNomeTexto.getSelectedIndex());
        CapitalDeGiro capital = capitais.get(capitalGiroCombo.getSelectedIndex());
        DespesasComVendas despesa = despesas.get(tipoDespesaCombo.getSelectedIndex());

        double quantidade = produto.getQuantidade();
        double taxaRetornoCapital = Double.parseDouble(taxaRetornoCapitalTexto.getText());
        double descontoProg = Double.parseDouble(descontoProgramado.getText());

        List<ComposicaoMateriaPrima> composicoesMateria = new ArrayList<>();
        List<ComposicaoCentroCusto> composicoesCentro = new ArrayList<>();
        List<InvestimentoFixo> investimentos = new ArrayList<>();

        try {
            composicoesMateria = composicaoMateriaDao.listarTodos();
            composicoesCentro = composicaoCentroDao.listarTodos();
            investimentos = investimentoDao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados do cálculo: " + e.getMessage());
            return;
        }

        // 4.1 - Custos apurados (vêm da composição do produto)

        // total_reposicao = custo_materia_prima (custo variável)
        double totalReposicao = 0;
        for (ComposicaoMateriaPrima c : composicoesMateria) {
            if (c.getIdProdutoVenda() == produto.getId()) {
                totalReposicao = totalReposicao + c.getTotal();
            }
        }

        // total_custo_fixo = custo_transformacao (custo fixo rateado)
        double totalCustoFixo = 0;
        for (ComposicaoCentroCusto c : composicoesCentro) {
            if (c.getIdProdutoVenda() == produto.getId()) {
                totalCustoFixo = totalCustoFixo + c.getTotal();
            }
        }

        // total = total_reposicao + total_custo_fixo
        double total = totalReposicao + totalCustoFixo;

        if (total == 0) {
            JOptionPane.showMessageDialog(this, "Este produto não tem composição cadastrada.");
            return;
        }

        // percentual_fixo = total_custo_fixo / total × 100
        double percentualFixo = totalCustoFixo / total * 100;

        // 4.2 - Remuneração do capital

        // investimento_fixo = Σ investimento_fixo.valor
        double investimentoFixo = 0;
        for (InvestimentoFixo i : investimentos) {
            investimentoFixo = investimentoFixo + i.getValor();
        }

        double investimentoVariavel = capital.getInvestimentoVariavel();

        // capital_investido = investimento_fixo + investimento_variavel
        double capitalInvestido = investimentoFixo + investimentoVariavel;

        // retorno_exigido = capital_investido × (taxa_retorno_capital / 100)
        double retornoExigido = capitalInvestido * (taxaRetornoCapital / 100);

        // 4.3 - Receita necessária (markup divisor)

        // receita_liquida = total + retorno_exigido
        // os dois são do lote: o custo vem das composições e o retorno é do
        // capital inteiro. A divisão por unidade acontece só no preço final.
        double receitaLiquida = total + retornoExigido;

        // p = percentual da despesa de venda / 100
        double p = despesa.getPercentual() / 100;

        // receita_bruta = receita_liquida / (1 − p)
        // divide-se em vez de somar porque a despesa incide sobre a receita,
        // que ainda não se conhece no momento do cálculo
        double receitaBruta = receitaLiquida / (1 - p);

        // despesa_venda = receita_bruta − receita_liquida
        double despesaVenda = receitaBruta - receitaLiquida;

        // 4.4 - Preço final

        // preco_final_unitario = receita_bruta / quantidade
        double precoFinalUnitario = receitaBruta / quantidade;

        // preco_venda_desconto = preco_final_unitario × (1 − desconto_programado / 100)
        double precoVendaDesconto = precoFinalUnitario * (1 - descontoProg / 100);

        // aumento = desconto_programado / (100 − desconto_programado) × 100
        double aumento = descontoProg / (100 - descontoProg) * 100;

        // margem_lucro = (receita_liquida − total) / receita_bruta × 100
        double margemLucro = (receitaLiquida - total) / receitaBruta * 100;

        // 4.5 - Ponto de equilíbrio

        // custo_variavel_unitario = (total_reposicao / quantidade) + (preco_final_unitario × p)
        double custoVariavelUnitario = (totalReposicao / quantidade) + (precoFinalUnitario * p);

        // margem_contribuicao = preco_final_unitario − custo_variavel_unitario
        double margemContribuicao = precoFinalUnitario - custoVariavelUnitario;

        // ponto_equilibrio_fixo = total_custo_fixo / margem_contribuicao (em unidades)
        double pontoEquilibrioFixo = totalCustoFixo / margemContribuicao;

        // ponto_equilibrio_variavel = ponto_equilibrio_fixo × preco_final_unitario (em R$)
        double pontoEquilibrioVariavel = pontoEquilibrioFixo * precoFinalUnitario;

        // mostra os valores calculados na tela
        totalReposicaoEstoqueTexto.setText(String.valueOf(totalReposicao));
        totalCustoFixoTexto.setText(String.valueOf(totalCustoFixo));
        investimentoFixoTexto.setText(String.valueOf(investimentoFixo));
        investimentoVariavelTexto.setText(String.valueOf(investimentoVariavel));
        receitaLiquidaTexto.setText(String.valueOf(receitaLiquida));
        despesaVendaTexto.setText(String.valueOf(despesaVenda));
        receitaBrutaTexto.setText(String.valueOf(receitaBruta));
        precoValorUnitario2Texto.setText(String.valueOf(precoFinalUnitario));
        precoValorUnitarioTexto.setText(String.valueOf(precoVendaDesconto));
        custoVariavelUnitarioTexto.setText(String.valueOf(custoVariavelUnitario));

        // grava o snapshot do cálculo
        ResultadoAdministrativo r = new ResultadoAdministrativo();
        r.setDataCalculo(LocalDateTime.now());
        r.setIdProdutoVenda(produto.getId());
        r.setCodigoProduto(produto.getCodigo());
        r.setNomeProduto(produto.getNome());
        r.setUnidade(produto.getUnidade());
        r.setTotal(total);
        r.setInvestimentoFixo(investimentoFixo);
        r.setInvestimentoVariavel(investimentoVariavel);
        r.setTotalReposicao(totalReposicao);
        r.setTotalCustoFixo(totalCustoFixo);
        r.setPercentualFixo(percentualFixo);
        r.setTaxaRetornoCapital(taxaRetornoCapital);
        r.setReceitaLiquida(receitaLiquida);
        r.setTipoDespesa(despesa.getNomeConta());
        r.setDespesaVenda(despesaVenda);
        r.setReceitaBruta(receitaBruta);
        r.setPrecoFinalUnitario(precoFinalUnitario);
        r.setPrecoVendaDesconto(precoVendaDesconto);
        r.setDescontoProgramado(descontoProg);
        r.setAumento(aumento);
        r.setPontoEquilibrioFixo(pontoEquilibrioFixo);
        r.setPontoEquilibrioVariavel(pontoEquilibrioVariavel);
        r.setMargemLucro(margemLucro);

        try {
            resultadoDao.inserir(r);
            JOptionPane.showMessageDialog(this, "Resultado calculado com sucesso!"
                    + "\nPreço de venda: " + precoFinalUnitario
                    + "\nMargem de lucro: " + margemLucro + "%"
                    + "\nPonto de equilíbrio: " + pontoEquilibrioFixo + " unidades");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao gravar o resultado: " + e.getMessage());
        }
    }//GEN-LAST:event_resultadoBotaoActionPerformed

    private void capitalGiroComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capitalGiroComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_capitalGiroComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> capitalGiroCombo;
    private javax.swing.JTextField custoVariavelUnitarioTexto;
    private javax.swing.JTextField descontoProgramado;
    private javax.swing.JTextField despesaVendaTexto;
    private javax.swing.JTextField investimentoFixoTexto;
    private javax.swing.JTextField investimentoVariavelTexto;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField precoValorUnitario2Texto;
    private javax.swing.JTextField precoValorUnitarioTexto;
    private javax.swing.JComboBox<String> produtoNomeTexto;
    private javax.swing.JTextField receitaBrutaTexto;
    private javax.swing.JTextField receitaLiquidaTexto;
    private javax.swing.JButton resultadoBotao;
    private javax.swing.JTextField taxaRetornoCapitalTexto;
    private javax.swing.JComboBox<String> tipoDespesaCombo;
    private javax.swing.JTextField totalCustoFixoTexto;
    private javax.swing.JTextField totalReposicaoEstoqueTexto;
    // End of variables declaration//GEN-END:variables
}
