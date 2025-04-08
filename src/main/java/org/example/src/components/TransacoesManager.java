package org.example.src.components;

import javax.swing.*;
import java.awt.*;

public class TransacoesManager extends JPanel {

    public static TransicoesPanelComponents createTransicoesPanel() {
        JPanel panelTransicoes = new JPanel();
        panelTransicoes.setLayout(new GridLayout(3, 1, 5, 5));

        // Botões
        JButton receitaButton = new JButton("Receita");
        Styles.setDefaultButtonSize(receitaButton);
        receitaButton.setVerticalTextPosition(AbstractButton.CENTER);

        JButton despesaButton = new JButton("Despesa");
        Styles.setDefaultButtonSize(despesaButton);
        despesaButton.setVerticalTextPosition(AbstractButton.CENTER);

        JButton voltarButton = new JButton("Voltar para o menu");
        Styles.setDefaultButtonSize(voltarButton);
        voltarButton.setVerticalTextPosition(AbstractButton.CENTER);

        // Adicionar botões ao painel
        panelTransicoes.add(receitaButton);
        panelTransicoes.add(despesaButton);
        panelTransicoes.add(voltarButton);

        // Criar e retornar o objeto de componentes
        return new TransicoesPanelComponents(
                panelTransicoes,
                receitaButton,
                despesaButton,
                voltarButton
        );
    }

    // Classe interna para armazenar os componentes do painel
    public static class TransicoesPanelComponents {
        public final JPanel panel;
        public final JButton receitaButton;
        public final JButton despesaButton;
        public final JButton voltarButton;

        public TransicoesPanelComponents(
                JPanel panel,
                JButton receitaButton,
                JButton despesaButton,
                JButton voltarButton) {
            this.panel = panel;
            this.receitaButton = receitaButton;
            this.despesaButton = despesaButton;
            this.voltarButton = voltarButton;
        }
    }
}