package view;

import dao.DataSource;
import dao.PrevisaoReposicaoEstoqueDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.PrevisaoReposicaoEstoque;

public class ListagemPrevisaoReposicaoEstoque extends javax.swing.JInternalFrame {

    private PrevisaoReposicaoEstoqueDAO dao = new PrevisaoReposicaoEstoqueDAO(new DataSource());

    public ListagemPrevisaoReposicaoEstoque() {
        initComponents();
        carregarTabela();
    }

    private void carregarTabela() {
        List<PrevisaoReposicaoEstoque> previsoes = new ArrayList<>();

        try {
            previsoes = dao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela: " + e.getMessage());
            return;
        }

        // uma linha por competência, somando os gastos de todos os lançamentos do mês
        List<PrevisaoReposicaoEstoque> competencias = new ArrayList<>();
        for (PrevisaoReposicaoEstoque p : previsoes) {
            boolean existe = false;
            for (PrevisaoReposicaoEstoque c : competencias) {
                if (c.getCompetencia().equals(p.getCompetencia())) {
                    existe = true;
                }
            }
            if (!existe) {
                competencias.add(p);
            }
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (PrevisaoReposicaoEstoque c : competencias) {
            double total = 0;
            for (PrevisaoReposicaoEstoque p : previsoes) {
                if (p.getCompetencia().equals(c.getCompetencia())) {
                    total = total + p.getGastosTotais();
                }
            }

            model.addRow(new Object[]{
                c.getCompetencia().getMonthValue(), c.getCompetencia().getYear(), total
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Previsão de reposição em estoque");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mes", "Ano", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listagem de Previsão de Reposição em Estoque");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
