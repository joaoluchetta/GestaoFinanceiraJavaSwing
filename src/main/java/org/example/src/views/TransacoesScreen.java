package org.example.src.components;

import org.example.src.views.DespesaScreen;
import org.example.src.views.ReceitaScreen;
import org.example.src.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransacoesScreen extends JFrame implements ActionListener {
    // Componentes da tela
    private JButton receitaButton;
    private JButton despesaButton;
    private JButton voltarButton;

    // Referência ao usuário logado
    private Usuario usuarioLogado;

    public TransacoesScreen() {
        this(null); // Chama o construtor com usuário nulo (para compatibilidade)
    }

    public TransacoesScreen(Usuario usuario) {
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Cadastro de Transações");

        // Criar painel de transições usando o TransicoesManager
        TransacoesManager.TransicoesPanelComponents transComponents = TransacoesManager.createTransicoesPanel();

        // Armazenar referências aos componentes para uso posterior
        receitaButton = transComponents.receitaButton;
        despesaButton = transComponents.despesaButton;
        voltarButton = transComponents.voltarButton;

        // Registrar este objeto como listener dos botões
        receitaButton.addActionListener(this);
        despesaButton.addActionListener(this);
        voltarButton.addActionListener(this);

        // Adicionar o painel ao frame
        add(transComponents.panel);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receitaButton) {
            dispose();
            new ReceitaScreen();
        } else if (e.getSource() == despesaButton) {
            dispose();
            new DespesaScreen();
        } else if (e.getSource() == voltarButton) {
            dispose();
        }
    }
}