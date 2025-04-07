package org.example.src.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HomeManager extends JPanel {

    public static HomePanelComponents createHomePanel() {
        JPanel panelHome = new JPanel();
        panelHome.setLayout(new BorderLayout(10, 10));
        //panelHome.setPreferredSize(new Dimension(1000, 800));
        
        //Panel dos buttons
        JPanel panelHomeButtons = new JPanel();
        panelHomeButtons.setLayout(new GridLayout(1, 4, 10, 10));
        //panelHomeButtons.setPreferredSize(new Dimension(1000, 30)); // Define altura fixa
        panelHomeButtons.setMaximumSize(new Dimension(Short.MAX_VALUE, 30)); // Impede crescimento vertical
        JButton buttonTela1 = new JButton("TELA 1");
        Styles.setDefaultButtonSize(buttonTela1);
        JButton buttonTela2 = new JButton("TELA 2");
        Styles.setDefaultButtonSize(buttonTela2);
        JButton buttonTela3 = new JButton("TELA 3");
        Styles.setDefaultButtonSize(buttonTela3);
        JButton buttonSair = new JButton("Sair");
        Styles.setDefaultButtonSize(buttonSair);
        
        panelHomeButtons.add(buttonTela1);
        panelHomeButtons.add(buttonTela2);
        panelHomeButtons.add(buttonTela3);
        panelHomeButtons.add(buttonSair);
        
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
        //NECESSÁRIO CRIAR UMA CLASSE PARA ESSE OBJETO AQUI (TRANSACAO CLASS OU USER CLASS)
        // Dados de exemplo apenas para demonstracao
        String[] colunas = {"Data", "Descrição", "Categoria", "Valor"};
        Object[][] dados = {
                {"01/04/2025", "Salário", "Receita", "R$ 3.500,00"},
                {"05/04/2025", "Aluguel", "Despesa", "R$ 1.200,00"},
                {"10/04/2025", "Supermercado", "Despesa", "R$ 450,00"}
        };

        // Criar o modelo da tabela
        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas);
        // Criar a tabela com o modelo
        JTable table = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(table);
        panelHomeTables.add(scrollPane, BorderLayout.CENTER);
        Styles.setDefaultTableSize(table);
        Styles.setTableHeaderStyle(table);
        
        // Panel principal que contém os 3 panels
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(panelHomeButtons);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre painéis
        contentPanel.add(panelHomeLabels);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre painéis
        contentPanel.add(panelHomeTables);
        
        panelHome.add(contentPanel, BorderLayout.CENTER);
        
        return new HomePanelComponents(
                panelHome,
                buttonTela1,
                buttonTela2,
                buttonTela3,
                buttonSair,
                labelSaldoTotal,
                labelReceitas,
                labelDespesas,
                table
        );
    }
    
    public static class HomePanelComponents {
        public final JPanel panelHome;
        public final JButton tela1Button;
        public final JButton tela2Button;
        public final JButton tela3Button;
        public final JButton sairButton;
        public final JLabel labelSaldoTotal;
        public final JLabel labelReceitas;
        public final JLabel labelDespesas;
        public final JTable table;
        
        public HomePanelComponents(
                JPanel panelHome,
                JButton tela1Button,
                JButton tela2Button,
                JButton tela3Button,
                JButton sairButton,
                JLabel labelSaldoTotal,
                JLabel labelReceitas,
                JLabel labelDespesas,
                JTable table) {
            this.panelHome = panelHome;
            this.tela1Button = tela1Button;
            this.tela2Button = tela2Button;
            this.tela3Button = tela3Button;
            this.sairButton = sairButton;
            this.labelSaldoTotal = labelSaldoTotal;
            this.labelReceitas = labelReceitas;
            this.labelDespesas = labelDespesas;
            this.table = table;
            
        }
    }
}
