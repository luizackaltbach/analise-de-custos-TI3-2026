
package view;

import dao.CentroCustoDAO;
import dao.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CentroCusto;

public class CadastroCentroCustos extends javax.swing.JInternalFrame {

    private List<CentroCusto> centrosCusto = new ArrayList<>();
    private CentroCustoDAO dao = new CentroCustoDAO(new DataSource());

    public CadastroCentroCustos() {
        initComponents();
        carregarTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nomeTexto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        horasEfetivasTexto = new javax.swing.JTextField();
        incluirBotao = new javax.swing.JButton();
        atualizarBotao = new javax.swing.JButton();
        excluirBotao = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/feito v.png"))); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/menos.png"))); // NOI18N

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/setapcima.png"))); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/fechar X.png"))); // NOI18N

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/return.png"))); // NOI18N

        jButton9.addActionListener(this::jButton9ActionPerformed);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/final.png"))); // NOI18N

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mais.png"))); // NOI18N

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Centro de custos");

        jLabel2.setText("Centro de custos");

        nomeTexto.addActionListener(this::nomeTextoActionPerformed);

        jLabel3.setText("Horas Efetivas");

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
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(incluirBotao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(atualizarBotao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(excluirBotao))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(377, 377, 377)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(nomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(horasEfetivasTexto))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horasEfetivasTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incluirBotao)
                    .addComponent(atualizarBotao)
                    .addComponent(excluirBotao))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Centro Custo", "Horas Efetivas"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jScrollPane2.setViewportView(jScrollPane1);

        jScrollPane4.setViewportView(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    }//GEN-LAST:event_jButton9ActionPerformed

    private void nomeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeTextoActionPerformed
    }//GEN-LAST:event_nomeTextoActionPerformed

    private void incluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirBotaoActionPerformed
        String nome = nomeTexto.getText();
        int horasEfetivas = Integer.parseInt(horasEfetivasTexto.getText());

        CentroCusto centroCusto = new CentroCusto(nome, horasEfetivas);

        try {
            dao.inserir(centroCusto);
            JOptionPane.showMessageDialog(this, "Centro de custo cadastrado com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
        }
    }//GEN-LAST:event_incluirBotaoActionPerformed

    private void atualizarBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarBotaoActionPerformed
        int linha = tabela.getSelectedRow();

        CentroCusto c = centrosCusto.get(linha);
        c.setNome(nomeTexto.getText());
        c.setHorasEfetivas(Integer.parseInt(horasEfetivasTexto.getText()));

        try {
            dao.alterar(c);
            JOptionPane.showMessageDialog(this, "Centro de custo atualizado com sucesso!");
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + e.getMessage());
        }
    }//GEN-LAST:event_atualizarBotaoActionPerformed

    private void excluirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirBotaoActionPerformed
        int linha = tabela.getSelectedRow();

        CentroCusto c = centrosCusto.get(linha);
        int id = c.getId();

        try {
            dao.excluir(id);
            JOptionPane.showMessageDialog(this, "Centro de custo excluído com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
        }
    }//GEN-LAST:event_excluirBotaoActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int linha = tabela.getSelectedRow();

        CentroCusto c = centrosCusto.get(linha);
        nomeTexto.setText(c.getNome());
        horasEfetivasTexto.setText(String.valueOf(c.getHorasEfetivas()));
    }//GEN-LAST:event_tabelaMouseClicked

    public void carregarTabela() {
        try {
            centrosCusto = dao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela: " + e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        for (CentroCusto c : centrosCusto) {
            model.addRow(new Object[]{
                c.getNome(), c.getHorasEfetivas()
            });
        }
    }

    private void limparCampos() {
        nomeTexto.setText("");
        horasEfetivasTexto.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarBotao;
    private javax.swing.JButton excluirBotao;
    private javax.swing.JTextField horasEfetivasTexto;
    private javax.swing.JButton incluirBotao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField nomeTexto;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
