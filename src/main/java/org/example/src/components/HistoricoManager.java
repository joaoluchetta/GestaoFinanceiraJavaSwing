package org.example.src.components;

import org.example.src.models.RegistroGlobal;

import javax.swing.*;
import java.awt.*;

public class HistoricoManager extends JPanel {

    public static HistoricoPanelComponents createHistoricoPanel() {
        JPanel panelHistorico = new JPanel(new BorderLayout());

        // Área de texto para exibir registros
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        // Preencher a área de texto com os registros
        for (String registro : RegistroGlobal.getRegistros()) {
            areaTexto.append(registro + "\n");
        }

        // Adicionar área de texto em um painel com scroll
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        // Botão para voltar
        JButton voltarButton = new JButton("Voltar");
        Styles.setDefaultButtonSize(voltarButton);

        // Montar o painel
        panelHistorico.add(scrollPane, BorderLayout.CENTER);
        panelHistorico.add(voltarButton, BorderLayout.SOUTH);

        // Definir tamanho preferido para o painel
        panelHistorico.setPreferredSize(new Dimension(500, 300));

        // Criar e retornar o objeto de componentes
        return new HistoricoPanelComponents(
                panelHistorico,
                areaTexto,
                voltarButton
        );
    }

    // Classe interna para armazenar os componentes do painel
    public static class HistoricoPanelComponents {
        public final JPanel panel;
        public final JTextArea areaTexto;
        public final JButton voltarButton;

        public HistoricoPanelComponents(
                JPanel panel,
                JTextArea areaTexto,
                JButton voltarButton) {
            this.panel = panel;
            this.areaTexto = areaTexto;
            this.voltarButton = voltarButton;
        }
    }
}