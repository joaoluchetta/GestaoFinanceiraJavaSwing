package org.example.src.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HomeManager extends JPanel {

    public static HomePanelComponents createHomePanel() {
        JPanel panelHome = new JPanel();
        panelHome.setLayout(new BorderLayout(10, 10));

        //Panel dos buttons
        JPanel panelHomeButtons = new JPanel();
        panelHomeButtons.setLayout(new GridLayout(1, 4, 10, 10));
        panelHomeButtons.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Impede crescimento vertical
        JButton buttonTransicoes = new JButton("Transições");
        Styles.setDefaultButtonSize(buttonTransicoes);
        JButton buttonHistorico = new JButton("Histórico");
        Styles.setDefaultButtonSize(buttonHistorico);
        JButton buttonCategoria = new JButton("Categorias");
        Styles.setDefaultButtonSize(buttonCategoria);
        JButton buttonSair = new JButton("Sair");
        Styles.setDefaultButtonSize(buttonSair);

        panelHomeButtons.add(buttonTransicoes);
        panelHomeButtons.add(buttonHistorico);
        panelHomeButtons.add(buttonCategoria);
        panelHomeButtons.add(buttonSair);

        // Painel de filtro por data
        JPanel panelHomeFilter = new JPanel();
        panelHomeFilter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelHomeFilter.setBorder(BorderFactory.createTitledBorder("Filtrar por período"));

        // Data inicial com JSpinner
        JPanel panelDataInicial = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelDataInicial = new JLabel("Data inicial:");
        SpinnerDateModel modelDataInicial = new SpinnerDateModel();
        JSpinner spinnerDataInicial = new JSpinner(modelDataInicial);
        spinnerDataInicial.setEditor(new JSpinner.DateEditor(spinnerDataInicial, "dd/MM/yyyy"));
        spinnerDataInicial.setPreferredSize(new Dimension(120, 25));
        panelDataInicial.add(labelDataInicial);
        panelDataInicial.add(spinnerDataInicial);

        // Data final com JSpinner
        JPanel panelDataFinal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelDataFinal = new JLabel("Data final:");
        SpinnerDateModel modelDataFinal = new SpinnerDateModel();
        JSpinner spinnerDataFinal = new JSpinner(modelDataFinal);
        spinnerDataFinal.setEditor(new JSpinner.DateEditor(spinnerDataFinal, "dd/MM/yyyy"));
        spinnerDataFinal.setPreferredSize(new Dimension(120, 25));
        panelDataFinal.add(labelDataFinal);
        panelDataFinal.add(spinnerDataFinal);

        // Botão de busca
        JButton buttonBuscar = new JButton("Buscar");
        Styles.setDefaultButtonSize(buttonBuscar);

        // Adicionar todos os componentes ao painel de filtro
        panelHomeFilter.add(panelDataInicial);
        panelHomeFilter.add(panelDataFinal);
        panelHomeFilter.add(buttonBuscar);

        //Panel das labels
        JPanel panelHomeLabels = new JPanel();
        panelHomeLabels.setLayout(new GridLayout(1, 4, 10, 10));
        JLabel labelSaldoTotal = new JLabel("Saldo Total:");
        Styles.setDefaultLabelSize(labelSaldoTotal);
        JLabel labelReceitas = new JLabel("Receitas: ");
        Styles.setDefaultLabelSize(labelReceitas);
        JLabel labelDespesas = new JLabel("Despesas:");
        Styles.setDefaultLabelSize(labelDespesas);

        panelHomeLabels.add(labelSaldoTotal);
        panelHomeLabels.add(labelReceitas);
        panelHomeLabels.add(labelDespesas);

        //Panel da table
        JPanel panelHomeTables = new JPanel();
        panelHomeTables.setLayout(new GridLayout(1, 4, 10, 10));

        // Definir colunas da tabela
        String[] colunas = {"Data", "Descrição", "Categoria", "Valor"};
        Object[][] dados = { };

        // Criar o modelo da tabela
        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas);

        // Criar a tabela com o modelo
        JTable table = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(table);
        panelHomeTables.add(scrollPane, BorderLayout.CENTER);
        Styles.setDefaultTableSize(table);
        Styles.setTableHeaderStyle(table);

        // Panel principal que contém todos os painéis
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(panelHomeButtons);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre painéis
        contentPanel.add(panelHomeFilter); // Adicionando o painel de filtro
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(panelHomeLabels);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(panelHomeTables);

        panelHome.add(contentPanel, BorderLayout.CENTER);

        return new HomePanelComponents(
                panelHome,
                buttonTransicoes,
                buttonHistorico,
                buttonCategoria,
                buttonSair,
                spinnerDataInicial,
                modelDataInicial,
                spinnerDataFinal,
                modelDataFinal,
                buttonBuscar,
                labelSaldoTotal,
                labelReceitas,
                labelDespesas,
                table
        );
    }

    public static class HomePanelComponents {
        public final JPanel panelHome;
        public final JButton buttonTransicoes;
        public final JButton buttonHistorico;
        public final JButton buttonCategoria;
        public final JButton sairButton;
        public final JSpinner dataInicialSpinner;
        public final SpinnerDateModel dataInicialModel;
        public final JSpinner dataFinalSpinner;
        public final SpinnerDateModel dataFinalModel;
        public final JButton buscarButton;
        public final JLabel labelSaldoTotal;
        public final JLabel labelReceitas;
        public final JLabel labelDespesas;
        public final JTable table;

        public HomePanelComponents(
                JPanel panelHome,
                JButton buttonTransicoes,
                JButton buttonHistorico,
                JButton buttonCategoria,
                JButton sairButton,
                JSpinner dataInicialSpinner,
                SpinnerDateModel dataInicialModel,
                JSpinner dataFinalSpinner,
                SpinnerDateModel dataFinalModel,
                JButton buscarButton,
                JLabel labelSaldoTotal,
                JLabel labelReceitas,
                JLabel labelDespesas,
                JTable table) {
            this.panelHome = panelHome;
            this.buttonTransicoes = buttonTransicoes;
            this.buttonHistorico = buttonHistorico;
            this.buttonCategoria = buttonCategoria;
            this.sairButton = sairButton;
            this.dataInicialSpinner = dataInicialSpinner;
            this.dataInicialModel = dataInicialModel;
            this.dataFinalSpinner = dataFinalSpinner;
            this.dataFinalModel = dataFinalModel;
            this.buscarButton = buscarButton;
            this.labelSaldoTotal = labelSaldoTotal;
            this.labelReceitas = labelReceitas;
            this.labelDespesas = labelDespesas;
            this.table = table;
        }
    }
}