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
    
    private JButton sairButton;
    private JButton buscarButton;
    private JButton buttonTransicoes;
    private JButton buttonHistorico;
    private JButton buttonCategoria;
    private JSpinner dataInicialSpinner;
    private SpinnerDateModel dataInicialModel;
    private JSpinner dataFinalSpinner;
    private SpinnerDateModel dataFinalModel;
    private JTable table;
    private JLabel labelSaldoTotal;
    private JLabel labelReceitas;
    private JLabel labelDespesas;
    
    private Usuario usuarioLogado;
    
    private final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public HomeScreen(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Não é possível criar HomeScreen sem um usuário");
        }
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Resumo Financeiro - " + usuario.getName() + " - (ID: " + usuarioLogado.getId() + ")");
        
        HomeManager.HomePanelComponents homeComponents = HomeManager.createHomePanel();
        
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
        
        sairButton.addActionListener(this);
        buscarButton.addActionListener(this);
        buttonTransicoes.addActionListener(this);
        buttonHistorico.addActionListener(this);
        buttonCategoria.addActionListener(this);
        
        add(homeComponents.panelHome);
        buscarTodosDados();
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }
    
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


    private void handleSair() {
        int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            dispose();
            new LoginScreen();
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
    
    private void handleBusca() {
        try {
            Date dataInicial = dataInicialModel.getDate();
            Date dataFinal = dataFinalModel.getDate();
            
            if (dataInicial == null || dataFinal == null) {
                buscarTodosDados();
                return;
            }
            
            if (dataInicial.after(dataFinal)) {
                JOptionPane.showMessageDialog(this,
                        "A data inicial não pode ser posterior à data final.",
                        "Datas inválidas", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String dataInicialStr = DATA_FORMAT.format(dataInicial);
            String dataFinalStr = DATA_FORMAT.format(dataFinal);
            System.out.println("Filtrando entre " + dataInicialStr + " e " + dataFinalStr);

            atualizarTabelaComTransacoes(filtrarPorPeriodo(dataInicialStr, dataFinalStr));

        } catch (Exception ex) {
            System.out.println("Erro no handleBusca: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao aplicar o filtro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarTodosDados() {
        try {
            List<Transacao> todasTransacoes = RegistroGlobal.getTransacoes();
            System.out.println("Buscando todas as transações: " + todasTransacoes.size() + " encontradas");
            
            atualizarTabelaComTransacoes(todasTransacoes);

        } catch (Exception e) {
            System.out.println("Erro em buscarTodosDados: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao buscar dados: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void atualizarTabelaComTransacoes(List<Transacao> transacoes) {
        try {
            if (table == null) {
                System.out.println("Erro: tabela esta vazia");
                return;
            }
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            if (model == null) {
                System.out.println("Erro: dados da tebela vazio");
                return;
            }
            model.setRowCount(0);
            
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
            }
            
            if (transacoes.isEmpty()) {
                model.addRow(new Object[]{
                        "---", "Nenhuma transação registrada", "---", "R$ 0,00"
                });
                labelReceitas.setText("Receitas: R$ 0,00");
                labelDespesas.setText("Despesas: R$ 0,00");
                labelSaldoTotal.setText("Saldo Total: R$ 0,00");
                return;
            }
            
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
            labelReceitas.setText("Receitas: R$ " + String.format("%.2f", receitas));
            labelDespesas.setText("Despesas: R$ " + String.format("%.2f", despesas));
            labelSaldoTotal.setText("Saldo Total: R$ " + String.format("%.2f", saldo));

        } catch (Exception e) {
            System.out.println("Erro ao atualizar tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}