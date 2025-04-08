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
    private JTextArea areaTexto;
    private JButton voltarButton;
    private final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public HistoricoScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Histórico de Registros");
        
        HistoricoManager.HistoricoPanelComponents historicoComponents = HistoricoManager.createHistoricoPanel();
        
        areaTexto = historicoComponents.areaTexto;
        voltarButton = historicoComponents.voltarButton;
        atualizarHistorico();
        voltarButton.addActionListener(this);
        add(historicoComponents.panel);
        
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    private void atualizarHistorico() {
        areaTexto.setText("");
        List<Transacao> transacoes = RegistroGlobal.getTransacoes();
        
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