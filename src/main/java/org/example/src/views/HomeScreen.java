package org.example.src.views;

import org.example.src.components.PanelManager;
import org.example.src.components.Styles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener {

    private JButton sairButton;

    public HomeScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Resumo Financeiro");

        // Criar painel principal  usando o PanelManager
        PanelManager.HomePanelComponents homeComponents = PanelManager.createHomePanel();

        // Armazenar referências aos componentes para uso posterior
        sairButton = homeComponents.sairButton;

        //Registra este objeto como listener dos botões
        sairButton.addActionListener(this);


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
        }
    }

    // Método para lidar com o login
    private void handleSair() {
            System.exit(0);
    }
}
