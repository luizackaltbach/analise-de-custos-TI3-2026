package view;

import dao.CentroCustoDAO;
import dao.ComposicaoCentroCustoDAO;
import dao.ComposicaoMateriaPrimaDAO;
import dao.DataSource;
import dao.MateriaPrimaDAO;
import dao.PrevisaoCustosFixosDAO;
import dao.ProdutosParaVendaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CentroCusto;
import model.ComposicaoCentroCusto;
import model.ComposicaoMateriaPrima;
import model.MateriaPrima;
import model.PrevisaoCustosFixos;
import model.ProdutosParaVenda;

public class CadastroComposicaoProdutos extends javax.swing.JInternalFrame {

    private DataSource ds = new DataSource();
    private ProdutosParaVendaDAO produtoDao = new ProdutosParaVendaDAO(ds);
    private ComposicaoMateriaPrimaDAO composicaoMateriaDao = new ComposicaoMateriaPrimaDAO(ds);
    private ComposicaoCentroCustoDAO composicaoCentroDao = new ComposicaoCentroCustoDAO(ds);
    private MateriaPrimaDAO materiaDao = new MateriaPrimaDAO(ds);
    private CentroCustoDAO centroDao = new CentroCustoDAO(ds);
    private PrevisaoCustosFixosDAO previsaoDao = new PrevisaoCustosFixosDAO(ds);

    private List<MateriaPrima> materias = new ArrayList<>();
    private List<CentroCusto> centros = new ArrayList<>();

    private List<ComposicaoMateriaPrima> composicoesMateria = new ArrayList<>();
    private List<ComposicaoCentroCusto> composicoesCentro = new ArrayList<>();

    public CadastroComposicaoProdutos() {
        initComponents();

        carregarCombos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jLabel1 = new javax.swing.JLabel();
        codigoTexto = new javax.swing.JTextField();
        nomeTexto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        materiaPrimaCombo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        quantidadeMateriaPrimaTexto = new javax.swing.JTextField();
        adicionarMateriaPrimaBotao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutoPrimario = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCentroCusto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        centroCustoCombo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        tempoMinutosCombo = new javax.swing.JTextField();
        adicionarCentroCustoBotao = new javax.swing.JButton();
        salvarBotao = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        quantidadeLote = new javax.swing.JTextField();
        unidadeCombo = new javax.swing.JComboBox<>();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Código");

        codigoTexto.addActionListener(this::codigoTextoActionPerformed);

        jLabel2.setText("Nome do Produto");

        jLabel3.setText("Unidade");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setText("Inserir Matéria Prima");

        materiaPrimaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Qtd:");

        adicionarMateriaPrimaBotao.setText("Adicionar");
        adicionarMateriaPrimaBotao.addActionListener(this::adicionarMateriaPrimaBotaoActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(materiaPrimaCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantidadeMateriaPrimaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adicionarMateriaPrimaBotao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materiaPrimaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(quantidadeMateriaPrimaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adicionarMateriaPrimaBotao))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        tabelaProdutoPrimario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Produto", "Unidade", "Quant", "Custo Rep."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaProdutoPrimario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutoPrimarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProdutoPrimario);
        if (tabelaProdutoPrimario.getColumnModel().getColumnCount() > 0) {
            tabelaProdutoPrimario.getColumnModel().getColumn(3).setHeaderValue("Quant");
            tabelaProdutoPrimario.getColumnModel().getColumn(4).setHeaderValue("Custo Rep.");
        }

        tabelaCentroCusto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Centro de Custo", "Tempo (min)", "Custo/Min"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaCentroCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCentroCustoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaCentroCusto);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setText("Centros de Custo do Produto.");

        centroCustoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Tempo(min)");

        adicionarCentroCustoBotao.setText("Adicionar");
        adicionarCentroCustoBotao.addActionListener(this::adicionarCentroCustoBotaoActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(centroCustoCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempoMinutosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adicionarCentroCustoBotao)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(centroCustoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tempoMinutosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adicionarCentroCustoBotao))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        salvarBotao.setText("Salvar");
        salvarBotao.addActionListener(this::salvarBotaoActionPerformed);

        jLabel4.setText("Quantidade do Lote");

        unidadeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  Kg", "  g", "  L", "  ml", "  un", "  m", "  m²", "  cx", "  pc" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unidadeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quantidadeLote, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(salvarBotao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unidadeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantidadeLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salvarBotao)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoTextoActionPerformed
    }//GEN-LAST:event_codigoTextoActionPerformed

    private void adicionarMateriaPrimaBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarMateriaPrimaBotaoActionPerformed
        MateriaPrima m = materias.get(materiaPrimaCombo.getSelectedIndex());
        double quantidade = Double.parseDouble(quantidadeMateriaPrimaTexto.getText());

        ComposicaoMateriaPrima c = new ComposicaoMateriaPrima();
        c.setIdMateriaPrima(m.getId());
        c.setQuantidade(quantidade);
        c.setCustoReposicao(m.getCustoReposicao());
        // total = quantidade × custo_reposicao (copiado agora: cópia histórica)
        c.setTotal(quantidade * m.getCustoReposicao());

        composicoesMateria.add(c);

        DefaultTableModel model = (DefaultTableModel) tabelaProdutoPrimario.getModel();
        model.addRow(new Object[]{
            m.getCodigo(), m.getNome(), m.getUnidade(), quantidade, m.getCustoReposicao()
        });

        quantidadeMateriaPrimaTexto.setText("");
    }//GEN-LAST:event_adicionarMateriaPrimaBotaoActionPerformed

    private void adicionarCentroCustoBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarCentroCustoBotaoActionPerformed
        CentroCusto centro = centros.get(centroCustoCombo.getSelectedIndex());
        double tempoMinutos = Double.parseDouble(tempoMinutosCombo.getText());

        List<PrevisaoCustosFixos> previsoes = new ArrayList<>();
        try {
            previsoes = previsaoDao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar o custo por minuto: " + e.getMessage());
            return;
        }

        // competência mais recente lançada para o centro
        java.time.LocalDate competencia = java.time.LocalDate.MIN;
        for (PrevisaoCustosFixos p : previsoes) {
            if (p.getIdCentroCusto() == centro.getId() && p.getCompetencia().isAfter(competencia)) {
                competencia = p.getCompetencia();
            }
        }

        // custo_minuto_do_centro = Σ custo_minuto das contas rateadas no centro
        double custoMinuto = 0;
        for (PrevisaoCustosFixos p : previsoes) {
            if (p.getIdCentroCusto() == centro.getId() && p.getCompetencia().equals(competencia)) {
                custoMinuto = custoMinuto + p.getCustoMinuto();
            }
        }

        ComposicaoCentroCusto c = new ComposicaoCentroCusto();
        c.setIdCentroCusto(centro.getId());
        c.setTempoMinutos(tempoMinutos);
        c.setCustoMinuto(custoMinuto);

        // total = tempo_minutos × custo_minuto
        c.setTotal(tempoMinutos * custoMinuto);

        composicoesCentro.add(c);

        DefaultTableModel model = (DefaultTableModel) tabelaCentroCusto.getModel();
        model.addRow(new Object[]{
            centro.getNome(), tempoMinutos, custoMinuto
        });

        tempoMinutosCombo.setText("");
    }//GEN-LAST:event_adicionarCentroCustoBotaoActionPerformed

    private void salvarBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBotaoActionPerformed
        String codigo = codigoTexto.getText();
        double quantidade = Double.parseDouble(quantidadeLote.getText());

        // custo_materia_prima = Σ totais da receita (custo variável)
        double custoMateriaPrima = 0;
        for (ComposicaoMateriaPrima c : composicoesMateria) {
            custoMateriaPrima = custoMateriaPrima + c.getTotal();
        }

        // custo_transformacao = Σ totais do roteiro (custo fixo rateado)
        double custoTransformacao = 0;
        for (ComposicaoCentroCusto c : composicoesCentro) {
            custoTransformacao = custoTransformacao + c.getTotal();
        }

        // custo_geral = custo_materia_prima + custo_transformacao
        double custoGeral = custoMateriaPrima + custoTransformacao;

        ProdutosParaVenda produto = new ProdutosParaVenda();
        produto.setCodigo(codigo);
        produto.setNome(nomeTexto.getText());
        produto.setUnidade(String.valueOf(unidadeCombo.getSelectedItem()));
        produto.setQuantidade(quantidade);
        produto.setCustoGeral(custoGeral);

        // custo_unitario = custo_geral / quantidade do lote
        produto.setCustoUnitario(custoGeral / quantidade);

        try {
            produtoDao.inserir(produto);

            for (ProdutosParaVenda item : produtoDao.listarTodos()) {
                if (item.getCodigo().equals(codigo)) {
                    produto = item;
                }
            }

            for (ComposicaoMateriaPrima c : composicoesMateria) {
                c.setIdProdutoVenda(produto.getId());
                composicaoMateriaDao.inserir(c);
            }

            for (ComposicaoCentroCusto c : composicoesCentro) {
                c.setIdProdutoVenda(produto.getId());
                composicaoCentroDao.inserir(c);
            }

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!"
                    + "\nCusto geral: " + custoGeral
                    + "\nCusto unitário: " + produto.getCustoUnitario());
            limparCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
        }
    }//GEN-LAST:event_salvarBotaoActionPerformed

    private void carregarCombos() {
        try {
            materias = materiaDao.listarTodos();
            centros = centroDao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os combos: " + e.getMessage());
            return;
        }

        materiaPrimaCombo.removeAllItems();
        for (MateriaPrima m : materias) {
            materiaPrimaCombo.addItem(m.getNome());
        }

        centroCustoCombo.removeAllItems();
        for (CentroCusto c : centros) {
            centroCustoCombo.addItem(c.getNome());
        }
    }

    private void limparCampos() {
        codigoTexto.setText("");
        nomeTexto.setText("");
        unidadeCombo.setSelectedIndex(0);
        quantidadeLote.setText("");
        quantidadeMateriaPrimaTexto.setText("");
        tempoMinutosCombo.setText("");

        composicoesMateria = new ArrayList<>();
        composicoesCentro = new ArrayList<>();

        ((DefaultTableModel) tabelaProdutoPrimario.getModel()).setRowCount(0);
        ((DefaultTableModel) tabelaCentroCusto.getModel()).setRowCount(0);
    }

    private void tabelaProdutoPrimarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutoPrimarioMouseClicked
    }//GEN-LAST:event_tabelaProdutoPrimarioMouseClicked

    private void tabelaCentroCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCentroCustoMouseClicked
    }//GEN-LAST:event_tabelaCentroCustoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarCentroCustoBotao;
    private javax.swing.JButton adicionarMateriaPrimaBotao;
    private javax.swing.JComboBox<String> centroCustoCombo;
    private javax.swing.JTextField codigoTexto;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> materiaPrimaCombo;
    private javax.swing.JTextField nomeTexto;
    private javax.swing.JTextField quantidadeLote;
    private javax.swing.JTextField quantidadeMateriaPrimaTexto;
    private javax.swing.JButton salvarBotao;
    private javax.swing.JTable tabelaCentroCusto;
    private javax.swing.JTable tabelaProdutoPrimario;
    private javax.swing.JTextField tempoMinutosCombo;
    private javax.swing.JComboBox<String> unidadeCombo;
    // End of variables declaration//GEN-END:variables
}
