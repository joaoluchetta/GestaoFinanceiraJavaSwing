package org.example.src.views;

import org.example.src.components.HomeManager;
import org.example.src.models.RegistroGlobal;
import org.example.src.components.Styles;
import org.example.src.components.TransacoesScreen;
import org.example.src.models.Transacao;
import org.example.src.models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.example.src.models.RegistroGlobal.filtrarPorPeriodo;

public class HomeScreen extends JFrame implements ActionListener {

    // Botões
    private JButton sairButton;
    private JButton buscarButton;
    private JButton buttonTransicoes;
    private JButton buttonHistorico;
    private JButton buttonCategoria;

    // Campos de data
    private JSpinner dataInicialSpinner;
    private SpinnerDateModel dataInicialModel;
    private JSpinner dataFinalSpinner;
    private SpinnerDateModel dataFinalModel;

    // Tabela de transações
    private JTable table;

    // Labels para mostrar totais
    private JLabel labelSaldoTotal;
    private JLabel labelReceitas;
    private JLabel labelDespesas;

    // Usuário logado
    private Usuario usuarioLogado;

    // Formato de data
    private final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

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
        buttonTransicoes = homeComponents.buttonTransicoes;
        buttonHistorico = homeComponents.buttonHistorico;
        buttonCategoria = homeComponents.buttonCategoria;
        dataInicialSpinner = homeComponents.dataInicialSpinner;
        dataInicialModel = homeComponents.dataInicialModel;
        dataFinalSpinner = homeComponents.dataFinalSpinner;
        dataFinalModel = homeComponents.dataFinalModel;
        table = homeComponents.table;
        labelSaldoTotal = homeComponents.labelSaldoTotal;
        labelReceitas = homeComponents.labelReceitas;
        labelDespesas = homeComponents.labelDespesas;

        // Registra este objeto como listener dos botões
        sairButton.addActionListener(this);
        buscarButton.addActionListener(this);
        buttonTransicoes.addActionListener(this);
        buttonHistorico.addActionListener(this);
        buttonCategoria.addActionListener(this);

        // Adicionar o painel ao frame
        add(homeComponents.panelHome);

        // Carregar os dados iniciais
        buscarTodosDados();

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    // Implementação do método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sairButton) {
            handleSair();
        } else if (e.getSource() == buscarButton) {
            handleBusca();
        } else if (e.getSource() == buttonTransicoes) {
            handleTransicoes();
        } else if (e.getSource() == buttonHistorico) {
            handleHistorico();
        } else if (e.getSource() == buttonCategoria) {
            handleCategoria();
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

    private void handleTransicoes() {
        TransacoesScreen transicoes = new TransacoesScreen(usuarioLogado);
        transicoes.setVisible(true);
    }

    private void handleHistorico() {
        HistoricoScreen historico = new HistoricoScreen();
        historico.setVisible(true);
    }

    private void handleCategoria() {
        CategoriaScreen categoriaScreen = new CategoriaScreen();
        categoriaScreen.setVisible(true);
    }

    // Método para tratar a busca por período
    private void handleBusca() {
        try {
            // Obter as datas dos spinners
            Date dataInicial = dataInicialModel.getDate();
            Date dataFinal = dataFinalModel.getDate();

            // Verificar se ambas as datas estão selecionadas
            if (dataInicial == null || dataFinal == null) {
                // Se alguma estiver vazia, busca todos os dados
                buscarTodosDados();
                return;
            }

            // Verifica se a data inicial é posterior à data final
            if (dataInicial.after(dataFinal)) {
                JOptionPane.showMessageDialog(this,
                        "A data inicial não pode ser posterior à data final.",
                        "Datas inválidas", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Formatar as datas para string
            String dataInicialStr = DATA_FORMAT.format(dataInicial);
            String dataFinalStr = DATA_FORMAT.format(dataFinal);

            System.out.println("Filtrando entre " + dataInicialStr + " e " + dataFinalStr);

            //filtrarPorPeriodo(dataInicialStr, dataFinalStr);

            atualizarTabelaComTransacoes(filtrarPorPeriodo(dataInicialStr, dataFinalStr));

        } catch (Exception ex) {
            System.out.println("Erro no handleBusca: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao aplicar o filtro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para buscar todos os dados
    private void buscarTodosDados() {
        try {
            // Obter todas as transações
            List<Transacao> todasTransacoes = RegistroGlobal.getTransacoes();
            System.out.println("Buscando todas as transações: " + todasTransacoes.size() + " encontradas");

            // Atualizar a tabela com as transações
            atualizarTabelaComTransacoes(todasTransacoes);

        } catch (Exception e) {
            System.out.println("Erro em buscarTodosDados: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao buscar dados: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método centralizado para atualizar a tabela com transações
    private void atualizarTabelaComTransacoes(List<Transacao> transacoes) {
        try {
            // Verificar se a tabela é nula
            if (table == null) {
                System.out.println("Erro: tabela é nula");
                return;
            }

            // Verificar se o modelo da tabela é nulo
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            if (model == null) {
                System.out.println("Erro: modelo da tabela é nulo");
                return;
            }

            // Limpar a tabela
            model.setRowCount(0);

            // Log para depuração
            System.out.println("Atualizando tabela com " + transacoes.size() + " transações");

            // Adicionar transações à tabela
            for (Transacao t : transacoes) {
                String tipo = t.isReceita() ? "Receita" : "Despesa";
                String dataFormatada = DATA_FORMAT.format(t.getData());
                String valorFormatado = "R$ " + String.format("%.2f", t.getValor());

                model.addRow(new Object[]{
                        dataFormatada,
                        t.getDescricao(),
                        tipo + ": " + t.getCategoria(),
                        valorFormatado
                });
                System.out.println("Adicionada transação: " + dataFormatada + " - " + t.getDescricao());
            }

            // Se não houver transações, adicionar uma linha indicando que está vazio
            if (transacoes.isEmpty()) {
                model.addRow(new Object[]{
                        "---", "Nenhuma transação registrada", "---", "R$ 0,00"
                });
                System.out.println("Tabela vazia - adicionada linha informativa");

                // Zerar os valores nas labels
                labelReceitas.setText("Receitas: R$ 0,00");
                labelDespesas.setText("Despesas: R$ 0,00");
                labelSaldoTotal.setText("Saldo Total: R$ 0,00");
                return;
            }

            // Calcular totais
            double receitas = 0;
            double despesas = 0;

            for (Transacao t : transacoes) {
                if (t.isReceita()) {
                    receitas += t.getValor();
                } else {
                    despesas += t.getValor();
                }
            }

            double saldo = receitas - despesas;

            // Atualizar os valores nas labels
            labelReceitas.setText("Receitas: R$ " + String.format("%.2f", receitas));
            labelDespesas.setText("Despesas: R$ " + String.format("%.2f", despesas));
            labelSaldoTotal.setText("Saldo Total: R$ " + String.format("%.2f", saldo));

            System.out.println("Totais atualizados: Receitas=" + receitas + ", Despesas=" + despesas + ", Saldo=" + saldo);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}