package view;

import dao.DataSource;
import dao.PrevisaoCustosFixosDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.PrevisaoCustosFixos;

public class ListagemPrevisaoCustosFixos extends javax.swing.JInternalFrame {

    private PrevisaoCustosFixosDAO dao = new PrevisaoCustosFixosDAO(new DataSource());

    public ListagemPrevisaoCustosFixos() {
        initComponents();
        carregarTabela();
    }

    private void carregarTabela() {
        List<PrevisaoCustosFixos> previsoes = new ArrayList<>();

        try {
            previsoes = dao.listarTodos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar a tabela: " + e.getMessage());
            return;
        }

        // uma linha por competência, somando o valor de todos os lançamentos do mês
        List<PrevisaoCustosFixos> competencias = new ArrayList<>();
        for (PrevisaoCustosFixos p : previsoes) {
            boolean existe = false;
            for (PrevisaoCustosFixos c : competencias) {
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
        for (PrevisaoCustosFixos c : competencias) {
            double total = 0;
            for (PrevisaoCustosFixos p : previsoes) {
                if (p.getCompetencia().equals(c.getCompetencia())) {
                    total = total + p.getValor();
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
        setTitle("Previsão de custos fixos");

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
        jLabel1.setText("Listagem de Previsão de Custos Fixos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
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
