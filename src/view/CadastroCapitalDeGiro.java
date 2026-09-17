
package view;

import dao.CapitalDeGiroDAO;
import dao.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CapitalDeGiro;

public class CadastroCapitalDeGiro extends javax.swing.JInternalFrame {

    private List<CapitalDeGiro> capitaisDeGiro = new ArrayList<>();
    private CapitalDeGiroDAO dao = new CapitalDeGiroDAO(new DataSource());

    public CadastroCapitalDeGiro() {
        initComponents();
        carregarTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        situacaoTexto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        prazoMedioTexto = new javax.swing.JTextField();
        periodoTexto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        investimentoVariavelTexto = new javax.swing.JTextField();
        incluirBotao = new javax.swing.JButton();
        atualizarBotao = new javax.swing.JButton();
        excluirBotao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/final.png"))); // NOI18N
        jButton18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mais.png"))); // NOI18N
        jButton19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-play-24.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/feito v.png"))); // NOI18N
        jButton12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/menos.png"))); // NOI18N
        jButton13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/setapcima.png"))); // NOI18N
        jButton14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/fechar X.png"))); // NOI18N
        jButton15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/return.png"))); // NOI18N
        jButton16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/inicio.png"))); // NOI18N
        jButton17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton17.addActionListener(this::jButton17ActionPerformed);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Capital de giro");

        jLabel2.setText("Situação");

        jLabel3.setText("Prazo Médio em dias");

        jLabel4.setText("Período em dias");

        jLabel5.setText("Investimento Variável");

        incluirBotao.setText("Incluir");
        incluirBotao.addActionListener(this::incluirBotaoActionPerformed);

        atualizarBotao.setText("Atualizar");
        atualizarBotao.addActionListener(this::atualizarBotaoActionPerformed);

        excluirBotao.setText("Excluir");
        excluirBotao.addActionListener(this::excluirBotaoActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(incluirBotao)
                        .addGap(18, 18, 18)
                        .addComponent(atualizarBotao)
                        .addGap(18, 18, 18)
                        .addComponent(excluirBotao))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(periodoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(investimentoVariavelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(situacaoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prazoMedioTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(situacaoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prazoMedioTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(periodoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(investimentoVariavelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incluirBotao)
                    .addComponent(atualizarBotao)
                    .addComponent(excluirBotao))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Situação", "Prazo médio em dias", "Período", "Investimento varíavel"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        jScrollPane1.setViewportView(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
    }//GEN-LAST:event_jButton17ActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int linha = tabela.getSelectedRow();

        CapitalDeGiro c = capitaisDeGiro.get(linha);
        situacaoTexto.setText(c.getSituacao());
        prazoMedioTexto.setText(String.valueOf(c.getPrazoMedioDias()));
        periodoTexto.setText(String.valueOf(c.getPeriodo()));
        investimentoVariavelTexto.setText(String.valueOf(c.getInvestimentoVariavel()));
    }//GEN-LAST:event_tabelaMouseClicked

    public void carregarTabela() {
        try {
            capitaisDeGiro = dao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela: " + e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        for (CapitalDeGiro c : capitaisDeGiro) {
            model.addRow(new Object[]{
                c.getSituacao(), c.getPrazoMedioDias(), c.getPeriodo(), c.getInvestimentoVariavel()
            });
        }
    }

    private void limparCampos() {
        situacaoTexto.setText("");
        prazoMedioTexto.setText("");
        periodoTexto.setText("");
        investimentoVariavelTexto.setText("");
    }

    private void incluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirBotaoActionPerformed
        String situacao = situacaoTexto.getText();
        double prazoMedioDias = Double.parseDouble(prazoMedioTexto.getText());
        double investimentoVariavel = Double.parseDouble(investimentoVariavelTexto.getText());
        double periodo = Double.parseDouble(periodoTexto.getText());

        CapitalDeGiro capitalDeGiro = new CapitalDeGiro(situacao, prazoMedioDias, investimentoVariavel, periodo);

        try {
            dao.inserir(capitalDeGiro);
            JOptionPane.showMessageDialog(this, "Capital de giro cadastrado com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
        }
    }//GEN-LAST:event_incluirBotaoActionPerformed

    private void atualizarBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarBotaoActionPerformed
        int linha = tabela.getSelectedRow();

        CapitalDeGiro c = capitaisDeGiro.get(linha);
        c.setSituacao(situacaoTexto.getText());
        c.setPrazoMedioDias(Double.parseDouble(prazoMedioTexto.getText()));
        c.setPeriodo(Double.parseDouble(periodoTexto.getText()));
        c.setInvestimentoVariavel(Double.parseDouble(investimentoVariavelTexto.getText()));

        try {
            dao.alterar(c);
            JOptionPane.showMessageDialog(this, "Capital de giro atualizado com sucesso!");
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
        }
    }//GEN-LAST:event_atualizarBotaoActionPerformed

    private void excluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirBotaoActionPerformed
        int linha = tabela.getSelectedRow();

        CapitalDeGiro c = capitaisDeGiro.get(linha);
        int id = c.getId();

        try {
            dao.excluir(id);
            JOptionPane.showMessageDialog(this, "Capital de giro excluído com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
        }
    }//GEN-LAST:event_excluirBotaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarBotao;
    private javax.swing.JButton excluirBotao;
    private javax.swing.JButton incluirBotao;
    private javax.swing.JTextField investimentoVariavelTexto;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField periodoTexto;
    private javax.swing.JTextField prazoMedioTexto;
    private javax.swing.JTextField situacaoTexto;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
