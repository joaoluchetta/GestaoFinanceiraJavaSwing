package org.example.src.views;

import org.example.src.models.Transacao;
import org.example.src.models.RegistroGlobal;
import org.example.src.components.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoricoScreen extends JFrame implements ActionListener {
    // Componentes da tela
    private JTextArea areaTexto;
    private JButton voltarButton;
    private final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public HistoricoScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Histórico de Registros");

        // Criar painel de histórico usando o HistoricoManager
        HistoricoManager.HistoricoPanelComponents historicoComponents = HistoricoManager.createHistoricoPanel();

        // Armazenar referências aos componentes para uso posterior
        areaTexto = historicoComponents.areaTexto;
        voltarButton = historicoComponents.voltarButton;

        // Atualizar o conteúdo da área de texto com as transações
        atualizarHistorico();

        // Registrar este objeto como listener dos botões
        voltarButton.addActionListener(this);

        // Adicionar o painel ao frame
        add(historicoComponents.panel);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    private void atualizarHistorico() {
        // Limpar a área de texto
        areaTexto.setText("");

        // Obter todas as transações
        List<Transacao> transacoes = RegistroGlobal.getTransacoes();

        // Formatar e adicionar cada transação
        for (Transacao t : transacoes) {
            String tipo = t.isReceita() ? "RECEITA" : "DESPESA";
            String linha = tipo + " | " +
                    DATA_FORMAT.format(t.getData()) + " | " +
                    t.getCategoria() + " | " +
                    t.getDescricao() + " | R$ " +
                    String.format("%.2f", t.getValor()) + "\n";

            areaTexto.append(linha);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltarButton) {
            handleVoltar();
        }
    }

    private void handleVoltar() {
        dispose();
    }
}