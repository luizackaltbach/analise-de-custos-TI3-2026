package view;

import dao.DataSource;
import dao.MateriaPrimaDAO;
import dao.PrevisaoReposicaoEstoqueDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.MateriaPrima;
import model.PrevisaoReposicaoEstoque;

public class CadastroPrevisaoReposicaoEstoque extends JInternalFrame {

    private DataSource ds = new DataSource();
    private PrevisaoReposicaoEstoqueDAO dao = new PrevisaoReposicaoEstoqueDAO(ds);
    private MateriaPrimaDAO materiaDao = new MateriaPrimaDAO(ds);

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private List<PrevisaoReposicaoEstoque> previsoes = new ArrayList<>();
    private List<MateriaPrima> materias = new ArrayList<>();

    

    public CadastroPrevisaoReposicaoEstoque() {
        initComponents();

        carregarMaterias();
        carregarTabela();
    }

    private void configurarCampos() throws ParseException {
            javax.swing.text.MaskFormatter mascara = new javax.swing.text.MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            competenciaData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascara));
            competenciaData.setValue(null);
        } 

    private LocalDate competencia() {
        return LocalDate.parse(competenciaData.getText(), FORMATO_DATA);
    }

    private MateriaPrima materia() {
        return materias.get(matprimaBox.getSelectedIndex());
    }

    /**
     * gastos_totais = (quantidade × custo_reposicao) + outros_gastos
     */
    private double gastosTotais(double quantidade, double custoReposicao, double outrosGastos) {
        return (quantidade * custoReposicao) + outrosGastos;
    }

    private void total() {
        double gastosTotais = gastosTotais(
                Double.parseDouble(quantidadeTexto.getText()),
                Double.parseDouble(custoReposicao.getText()),
                Double.parseDouble(outrosGastosTexto.getText()));

        gastosTotaisTexto.setText(String.valueOf(gastosTotais));
    }

    private MateriaPrima buscarMateria(int id) {
        for (MateriaPrima m : materias) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    private void selecionarMateria(int id) {
        for (int i = 0; i < materias.size(); i++) {
            if (materias.get(i).getId() == id) {
                matprimaBox.setSelectedIndex(i);
            }
        }
    }

    private void incluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirBotaoActionPerformed
        MateriaPrima m = materia();

        PrevisaoReposicaoEstoque p = new PrevisaoReposicaoEstoque();
        preencher(p, m);

        try {
            dao.inserir(p);
            JOptionPane.showMessageDialog(this, "Previsão cadastrada com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
        }
    }//GEN-LAST:event_incluirBotaoActionPerformed

    private void atualizarBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarBotaoActionPerformed
        int linha = reposicaoTable.getSelectedRow();

        PrevisaoReposicaoEstoque p = previsoes.get(linha);
        preencher(p, materia());

        try {
            dao.alterar(p);
            JOptionPane.showMessageDialog(this, "Previsão atualizada com sucesso!");
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
        }
    }//GEN-LAST:event_atualizarBotaoActionPerformed

    private void preencher(PrevisaoReposicaoEstoque p, MateriaPrima m) {
        p.setCompetencia(competencia());
        p.setSequencia(Integer.parseInt(sequenciaTexto.getText()));
        p.setIdMateriaPrima(m.getId());
        p.setQuantidade(Double.parseDouble(quantidadeTexto.getText()));
        p.setCustoReposicao(Double.parseDouble(custoReposicao.getText()));
        p.setOutrosGastos(Double.parseDouble(outrosGastosTexto.getText()));
    }

    private void excluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirBotaoActionPerformed
        int linha = reposicaoTable.getSelectedRow();

        PrevisaoReposicaoEstoque p = previsoes.get(linha);
        int id = p.getId();

        try {
            dao.excluir(id);
            JOptionPane.showMessageDialog(this, "Previsão excluída com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
        }
    }//GEN-LAST:event_excluirBotaoActionPerformed

    private void reposicaoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reposicaoTableMouseClicked
        int linha = reposicaoTable.getSelectedRow();

        PrevisaoReposicaoEstoque p = previsoes.get(linha);
        competenciaData.setText(p.getCompetencia().format(FORMATO_DATA));
        sequenciaTexto.setText(String.valueOf(p.getSequencia()));
        selecionarMateria(p.getIdMateriaPrima());
        quantidadeTexto.setText(String.valueOf(p.getQuantidade()));
        custoReposicao.setText(String.valueOf(p.getCustoReposicao()));
        outrosGastosTexto.setText(String.valueOf(p.getOutrosGastos()));

        total();
    }//GEN-LAST:event_reposicaoTableMouseClicked

    private void carregarMaterias() {
        try {
            materias = materiaDao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar as matérias-primas: " + e.getMessage());
            return;
        }

        matprimaBox.removeAllItems();
        for (MateriaPrima m : materias) {
            matprimaBox.addItem(m.getNome());
        }
    }

    public void carregarTabela() {
        try {
            previsoes = dao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela: " + e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) reposicaoTable.getModel();
        model.setRowCount(0);
        for (PrevisaoReposicaoEstoque p : previsoes) {
            MateriaPrima m = buscarMateria(p.getIdMateriaPrima());

            model.addRow(new Object[]{
                p.getCompetencia().format(FORMATO_DATA),
                p.getSequencia(),
                m.getId(),
                m.getNome(),
                p.getQuantidade(),
                p.getCustoReposicao(),
                p.getOutrosGastos(),
                gastosTotais(p.getQuantidade(), p.getCustoReposicao(), p.getOutrosGastos())
            });
        }
    }

    private void limparCampos() {
        competenciaData.setValue(null);
        sequenciaTexto.setText("");
        quantidadeTexto.setText("");
        custoReposicao.setText("");
        outrosGastosTexto.setText("");
        gastosTotaisTexto.setText("");
        matprimaBox.setSelectedIndex(0);

        reposicaoTable.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton19 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        sequenciaSpinner3 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        sequenciaSpinner4 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        competenciaData = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        matprimaBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        reposicaoTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        incluirBotao = new javax.swing.JButton();
        atualizarBotao = new javax.swing.JButton();
        excluirBotao = new javax.swing.JButton();
        quantidadeTexto = new javax.swing.JTextField();
        custoReposicao = new javax.swing.JTextField();
        outrosGastosTexto = new javax.swing.JTextField();
        gastosTotaisTexto = new javax.swing.JTextField();
        sequenciaTexto = new javax.swing.JTextField();

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mais.png"))); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-play-24.png"))); // NOI18N

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/feito v.png"))); // NOI18N

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/menos.png"))); // NOI18N

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/setapcima.png"))); // NOI18N

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/fechar X.png"))); // NOI18N

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/return.png"))); // NOI18N

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/inicio.png"))); // NOI18N
        jButton17.addActionListener(this::jButton17ActionPerformed);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/final.png"))); // NOI18N

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/fechar X.png"))); // NOI18N
        jButton30.setText("Fechar");

        jLabel6.setText("Sequencia");

        jLabel7.setText("Sequencia");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Previsão de reposição em estoque");

        jLabel1.setText("Competência");

        jLabel2.setText("Sequencia");

        matprimaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        matprimaBox.addActionListener(this::matprimaBoxActionPerformed);

        jLabel3.setText("Matéria Prima");

        jLabel4.setText("Quantidade");

        reposicaoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Competência", "Sequência", "Código", "Matéria-prima", "Quantidade", "Custo reposição", "Outros gastos	", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        reposicaoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reposicaoTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(reposicaoTable);

        jLabel5.setText("Custo de Reposição (R$)");

        jLabel8.setText("Outros Gastos(R$)");

        jLabel9.setText("Gastos Totais (R$)");

        incluirBotao.setText("Incluir");
        incluirBotao.addActionListener(this::incluirBotaoActionPerformed);

        atualizarBotao.setText("Atualizar");
        atualizarBotao.addActionListener(this::atualizarBotaoActionPerformed);

        excluirBotao.setText("Excluir");
        excluirBotao.addActionListener(this::excluirBotaoActionPerformed);

        gastosTotaisTexto.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matprimaBox, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(competenciaData, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(sequenciaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(incluirBotao)
                                .addGap(18, 18, 18)
                                .addComponent(atualizarBotao)
                                .addGap(18, 18, 18)
                                .addComponent(excluirBotao)))
                        .addContainerGap(114, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(quantidadeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(custoReposicao, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(outrosGastosTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(gastosTotaisTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(competenciaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sequenciaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matprimaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custoReposicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outrosGastosTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gastosTotaisTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(atualizarBotao)
                        .addComponent(excluirBotao))
                    .addComponent(incluirBotao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

    }//GEN-LAST:event_jButton17ActionPerformed

    private void matprimaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matprimaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matprimaBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarBotao;
    private javax.swing.JFormattedTextField competenciaData;
    private javax.swing.JTextField custoReposicao;
    private javax.swing.JButton excluirBotao;
    private javax.swing.JTextField gastosTotaisTexto;
    private javax.swing.JButton incluirBotao;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton30;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> matprimaBox;
    private javax.swing.JTextField outrosGastosTexto;
    private javax.swing.JTextField quantidadeTexto;
    private javax.swing.JTable reposicaoTable;
    private javax.swing.JSpinner sequenciaSpinner3;
    private javax.swing.JSpinner sequenciaSpinner4;
    private javax.swing.JTextField sequenciaTexto;
    // End of variables declaration//GEN-END:variables
}
