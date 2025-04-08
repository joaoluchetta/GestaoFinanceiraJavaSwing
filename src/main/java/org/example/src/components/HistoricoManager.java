package org.example.src.components;

import org.example.src.models.RegistroGlobal;

import javax.swing.*;
import java.awt.*;

public class HistoricoManager extends JPanel {

    public static HistoricoPanelComponents createHistoricoPanel() {
        JPanel panelHistorico = new JPanel(new BorderLayout());
        
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        
        for (String registro : RegistroGlobal.getRegistros()) {
            areaTexto.append(registro + "\n");
        }
        
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        JButton voltarButton = new JButton("Voltar");
        Styles.setDefaultButtonSize(voltarButton);
        
        panelHistorico.add(scrollPane, BorderLayout.CENTER);
        panelHistorico.add(voltarButton, BorderLayout.SOUTH);
        panelHistorico.setPreferredSize(new Dimension(500, 300));
        
        return new HistoricoPanelComponents(
                panelHistorico,
                areaTexto,
                voltarButton
        );
    }
    
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