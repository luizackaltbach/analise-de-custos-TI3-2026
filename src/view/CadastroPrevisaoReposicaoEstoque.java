package view;

import dao.DataSource;
import dao.MateriaPrimaDAO;
import dao.PrevisaoReposicaoEstoqueDAO;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.MateriaPrima;
import model.PrevisaoReposicaoEstoque;

public class CadastroPrevisaoReposicaoEstoque extends JInternalFrame {

    private final DataSource ds = new DataSource();
    private final PrevisaoReposicaoEstoqueDAO dao =
            new PrevisaoReposicaoEstoqueDAO(ds);
    private final MateriaPrimaDAO materiaDao =
            new MateriaPrimaDAO(ds);

    private List<PrevisaoReposicaoEstoque> previsoes = new ArrayList<>();
    private List<MateriaPrima> materias = new ArrayList<>();
    private PrevisaoReposicaoEstoque selecionada;

    

    public CadastroPrevisaoReposicaoEstoque() {
        super("Previsão de reposição em estoque",
                true, true, true, true);

        criarTela();

        try {
            carregarMaterias();
            carregarTabela();
        } catch (SQLException e) {
            erro(e);
        }

        setSize(760, 550);
    }

    private void criarTela() {
        setLayout(new BorderLayout(5, 5));

        JPanel campos = new JPanel(new GridLayout(4, 4, 5, 5));

        competenciaData = criarData();

        sequenciaSpinner =
                new JSpinner(new SpinnerNumberModel(0, 0, null, 1));

        quantidadeSpinner = decimal();
        custorepSpinner = decimal();
        outrosgastSpinner = decimal();
        gastostotaisSpinner = decimal();
        gastostotaisSpinner.setEnabled(false);

        matprimaBox = new JComboBox<>();

        campos.add(new JLabel("Competência"));
        campos.add(competenciaData);

        campos.add(new JLabel("Sequência"));
        campos.add(sequenciaSpinner);

        campos.add(new JLabel("Matéria Prima"));
        campos.add(matprimaBox);

        campos.add(new JLabel("Quantidade"));
        campos.add(quantidadeSpinner);

        campos.add(new JLabel("Custo reposição"));
        campos.add(custorepSpinner);

        campos.add(new JLabel("Outros gastos"));
        campos.add(outrosgastSpinner);

        campos.add(new JLabel("Total"));
        campos.add(gastostotaisSpinner);

        add(campos, BorderLayout.NORTH);

        reposicaoTable = new JTable(new DefaultTableModel(
                new Object[]{
                    "Competência",
                    "Sequência",
                    "Código",
                    "Matéria-prima",
                    "Quantidade",
                    "Custo reposição",
                    "Outros gastos",
                    "Total"
                }, 0
        ));

        add(new JScrollPane(reposicaoTable),
                BorderLayout.CENTER);

        JButton incluir = new JButton("Incluir");
        JButton salvar = new JButton("Salvar");
        JButton excluir = new JButton("Excluir");

        JPanel botoes = new JPanel();

        botoes.add(incluir);
        botoes.add(salvar);
        botoes.add(excluir);

        add(botoes, BorderLayout.SOUTH);

        incluir.addActionListener(e -> incluir());
        salvar.addActionListener(e -> salvar());
        excluir.addActionListener(e -> excluir());

        reposicaoTable.getSelectionModel().addListSelectionListener(
                e -> {
                    if (!e.getValueIsAdjusting())
                        carregarSelecionada();
                }
        );

        custorepSpinner.addChangeListener(e -> total());
        outrosgastSpinner.addChangeListener(e -> total());
    }

    private JSpinner decimal() {
        JSpinner s = new JSpinner(
                new SpinnerNumberModel(0.0, 0.0, null, 0.01)
        );

        s.setEditor(new JSpinner.NumberEditor(s, "0.00"));

        return s;
    }

    private JFormattedTextField criarData() {
        try {
            javax.swing.text.MaskFormatter m =
                    new javax.swing.text.MaskFormatter("##/##/####");

            m.setPlaceholderCharacter('_');

            return new JFormattedTextField(m);

        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDate competencia() {
        try {
            return LocalDate.parse(
                    competenciaData.getText(),
                    DateTimeFormatter.ofPattern("dd/MM/uuuu")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Informe uma competência válida."
            );
        }
    }

    private double valor(JSpinner s) {
        return ((Number) s.getValue()).doubleValue();
    }

    private MateriaPrima materia() {
        int i = matprimaBox.getSelectedIndex();

        return i >= 0 ? materias.get(i) : null;
    }

    private void total() {
        gastostotaisSpinner.setValue(
                valor(custorepSpinner)
                + valor(outrosgastSpinner)
        );
    }

    private void carregarMaterias() throws SQLException {
        materias = materiaDao.listarTodos();

        matprimaBox.removeAllItems();

        for (MateriaPrima m : materias)
            matprimaBox.addItem(m.getNome());
    }

    private MateriaPrima buscarMateria(int id) {
        for (MateriaPrima m : materias)
            if (m.getId() == id)
                return m;

        return null;
    }

    private void selecionarMateria(int id) {
        for (int i = 0; i < materias.size(); i++)
            if (materias.get(i).getId() == id)
                matprimaBox.setSelectedIndex(i);
    }

    private void incluir() {
        try {
            MateriaPrima m = materia();

            if (m == null)
                throw new IllegalArgumentException(
                        "Selecione uma matéria-prima."
                );

            PrevisaoReposicaoEstoque p =
                    new PrevisaoReposicaoEstoque();

            preencher(p, m);

            dao.inserir(p);

            limpar();
            carregarTabela();

            JOptionPane.showMessageDialog(
                    this,
                    "Previsão cadastrada!"
            );

        } catch (SQLException | IllegalArgumentException e) {
            erro(e);
        }
    }

    private void salvar() {
        if (selecionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um registro."
            );
            return;
        }

        try {
            MateriaPrima m = materia();

            if (m == null)
                throw new IllegalArgumentException(
                        "Selecione uma matéria-prima."
                );

            preencher(selecionada, m);

            dao.alterar(selecionada);

            limpar();
            carregarTabela();

            JOptionPane.showMessageDialog(
                    this,
                    "Previsão atualizada!"
            );

        } catch (SQLException | IllegalArgumentException e) {
            erro(e);
        }
    }

    private void preencher(
            PrevisaoReposicaoEstoque p,
            MateriaPrima m) {

        p.setCompetencia(competencia());

        p.setSequencia(
                ((Number) sequenciaSpinner.getValue()).intValue()
        );

        p.setIdMateriaPrima(m.getId());
        p.setQuantidade(valor(quantidadeSpinner));
        p.setCustoReposicao(valor(custorepSpinner));
        p.setOutrosGastos(valor(outrosgastSpinner));
    }

    private void excluir() {
        int linha = reposicaoTable.getSelectedRow();

        if (linha < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um registro."
            );
            return;
        }

        try {
            PrevisaoReposicaoEstoque p =
                    previsoes.get(linha);

            dao.excluir(p.getId());

            limpar();
            carregarTabela();

        } catch (SQLException e) {
            erro(e);
        }
    }

    private void carregarSelecionada() {
        int linha = reposicaoTable.getSelectedRow();

        if (linha < 0)
            return;

        selecionada = previsoes.get(linha);

        competenciaData.setText(
                selecionada.getCompetencia().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                )
        );

        sequenciaSpinner.setValue(
                selecionada.getSequencia()
        );

        selecionarMateria(
                selecionada.getIdMateriaPrima()
        );

        quantidadeSpinner.setValue(
                selecionada.getQuantidade()
        );

        custorepSpinner.setValue(
                selecionada.getCustoReposicao()
        );

        outrosgastSpinner.setValue(
                selecionada.getOutrosGastos()
        );

        total();
    }

    private void carregarTabela() throws SQLException {
        previsoes = dao.listarTodos();

        DefaultTableModel model =
                (DefaultTableModel) reposicaoTable.getModel();

        model.setRowCount(0);

        DateTimeFormatter f =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (PrevisaoReposicaoEstoque p : previsoes) {

            MateriaPrima m =
                    buscarMateria(p.getIdMateriaPrima());

            model.addRow(new Object[]{
                p.getCompetencia().format(f),
                p.getSequencia(),
                m != null ? m.getId() : null,
                m != null ? m.getNome() : "",
                p.getQuantidade(),
                p.getCustoReposicao(),
                p.getOutrosGastos(),
                p.getCustoReposicao()
                    + p.getOutrosGastos()
            });
        }
    }

    private void limpar() {
        competenciaData.setValue(null);
        sequenciaSpinner.setValue(0);
        quantidadeSpinner.setValue(0.0);
        custorepSpinner.setValue(0.0);
        outrosgastSpinner.setValue(0.0);
        gastostotaisSpinner.setValue(0.0);

        if (matprimaBox.getItemCount() > 0)
            matprimaBox.setSelectedIndex(0);

        reposicaoTable.clearSelection();
        selecionada = null;
    }

    private void erro(Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Erro: " + e.getMessage()
        );
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
        sequenciaSpinner = new javax.swing.JSpinner();
        matprimaBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        quantidadeSpinner = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        reposicaoTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        custorepSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        outrosgastSpinner = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        gastostotaisSpinner = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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
        jScrollPane2.setViewportView(reposicaoTable);

        jLabel5.setText("Custo de Reposição(R$)");

        jLabel8.setText("Outros Gastos(R$)");

        jLabel9.setText("Gastos Totais (R$)");

        jButton1.setText("Incluir");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton3.setText("Salvar");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton5.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(quantidadeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(custorepSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outrosgastSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gastostotaisSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(matprimaBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(competenciaData, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(sequenciaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(sequenciaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matprimaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantidadeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(custorepSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(outrosgastSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(gastostotaisSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

    }//GEN-LAST:event_jButton17ActionPerformed

    private void matprimaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matprimaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matprimaBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField competenciaData;
    private javax.swing.JSpinner custorepSpinner;
    private javax.swing.JSpinner gastostotaisSpinner;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JSpinner outrosgastSpinner;
    private javax.swing.JSpinner quantidadeSpinner;
    private javax.swing.JTable reposicaoTable;
    private javax.swing.JSpinner sequenciaSpinner;
    private javax.swing.JSpinner sequenciaSpinner3;
    private javax.swing.JSpinner sequenciaSpinner4;
    // End of variables declaration//GEN-END:variables
}
