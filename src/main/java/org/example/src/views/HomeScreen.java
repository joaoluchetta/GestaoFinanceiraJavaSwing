package org.example.src.views;

import org.example.src.components.HomeManager;
import org.example.src.components.Styles;
import org.example.src.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener {

    // Botões
    private JButton sairButton;
    private JButton buscarButton;

    // Campos de texto para datas
    private JTextField dataInicialField;
    private JTextField dataFinalField;

    // Tabela de transações
    private JTable table;

    // Labels para mostrar totais
    private JLabel labelSaldoTotal;
    private JLabel labelReceitas;
    private JLabel labelDespesas;

    // Usuário logado
    private Usuario usuarioLogado;

    public HomeScreen(Usuario usuario) {
        // Verifica se o usuário não é nulo
        if (usuario == null) {
            throw new IllegalArgumentException("Não é possível criar HomeScreen sem um usuário");
        }
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Resumo Financeiro - " + usuario.getName() + " - (ID: " + usuarioLogado.getId() + ")");

        // Criar painel principal usando o HomeManager
        HomeManager.HomePanelComponents homeComponents = HomeManager.createHomePanel();

        // Armazenar referências aos componentes para uso posterior
        sairButton = homeComponents.sairButton;
        buscarButton = homeComponents.buscarButton;
        dataInicialField = homeComponents.dataInicialField;
        dataFinalField = homeComponents.dataFinalField;
        table = homeComponents.table;
        labelSaldoTotal = homeComponents.labelSaldoTotal;
        labelReceitas = homeComponents.labelReceitas;
        labelDespesas = homeComponents.labelDespesas;

        // Registra este objeto como listener dos botões
        sairButton.addActionListener(this);
        buscarButton.addActionListener(this);

        // Adicionar o painel ao frame
        add(homeComponents.panelHome);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
    }

    // Implementação do método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sairButton) {
            handleSair();
        } else if (e.getSource() == buscarButton) {
            handleBusca();
        }
    }

    // Método para lidar com o login
    private void handleSair() {
        int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            dispose();
            new LoginScreen(); //volta para a tela de login
        }
    }

    // Método para tratar a busca por período
    private void handleBusca() {
        String dataInicial = dataInicialField.getText().trim();
        String dataFinal = dataFinalField.getText().trim();

        try {
            if (dataInicial.isEmpty() && dataFinal.isEmpty()) {
                buscarTodosDados();
            }

            else if (dataInicial.isEmpty() || dataFinal.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, informe ambas as datas ou deixe os dois campos vazios para buscar todos os dados.",
                        "Dados incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else {
                filtrarPorPeriodo(dataInicial, dataFinal);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao aplicar o filtro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para buscar todos os dados
    private void buscarTodosDados() {
        // Aqui você implementaria a lógica para buscar todos os dados
        // Por exemplo, chamando um método de serviço:
        // List<Transacao> todasTransacoes = transacaoService.buscarTodas();

        // Exemplo de atualização da tabela (simulado):
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela

        // Exemplo simples (simulado) - mostrando todos os dados
        model.addRow(new Object[]{"01/04/2025", "Salário", "Receita", "R$ 3.500,00"});
        model.addRow(new Object[]{"05/04/2025", "Aluguel", "Despesa", "R$ 1.200,00"});
        model.addRow(new Object[]{"10/04/2025", "Supermercado", "Despesa", "R$ 450,00"});
        model.addRow(new Object[]{"15/04/2025", "Freelance", "Receita", "R$ 800,00"});
        model.addRow(new Object[]{"20/04/2025", "Internet", "Despesa", "R$ 120,00"});

        // Atualizar os valores nas labels (simulado)
        double receitas = 4300.0; // 3500 + 800
        double despesas = 1770.0; // 1200 + 450 + 120
        double saldo = receitas - despesas;

        labelReceitas.setText("Receitas: R$ " + String.format("%.2f", receitas));
        labelDespesas.setText("Despesas: R$ " + String.format("%.2f", despesas));
        labelSaldoTotal.setText("Saldo Total: R$ " + String.format("%.2f", saldo));

        JOptionPane.showMessageDialog(this,
                "Exibindo todos os dados!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para filtrar por período
    private void filtrarPorPeriodo(String dataInicial, String dataFinal) {
        // Aqui você implementaria a lógica para filtrar os dados
        // Por exemplo, chamando um método de serviço:
        // List<Transacao> transacoesFiltradas = transacaoService.filtrarPorPeriodo(dataInicial, dataFinal);

        // Exemplo de atualização da tabela (simulado):
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela

        // Exemplo simples (simulado) - dados filtrados
        model.addRow(new Object[]{"10/04/2025", "Salário (Filtrado)", "Receita", "R$ 3.000,00"});
        model.addRow(new Object[]{"15/04/2025", "Internet (Filtrado)", "Despesa", "R$ 120,00"});

        // Atualizar os valores nas labels (simulado)
        double receitas = 3000.0;
        double despesas = 120.0;
        double saldo = receitas - despesas;

        labelReceitas.setText("Receitas: R$ " + String.format("%.2f", receitas));
        labelDespesas.setText("Despesas: R$ " + String.format("%.2f", despesas));
        labelSaldoTotal.setText("Saldo Total: R$ " + String.format("%.2f", saldo));

        JOptionPane.showMessageDialog(this,
                "Filtro aplicado com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}