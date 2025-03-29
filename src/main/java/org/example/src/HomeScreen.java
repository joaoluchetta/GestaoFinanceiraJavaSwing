package org.example.src;

import org.example.src.components.PanelManager;
import org.example.src.components.Styles;

import javax.swing.*;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    public HomeScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);
        setTitle("HomeScreen");

        // Criar painel principal  usando o PanelManager
        PanelManager.HomePanelComponents homeComponents = PanelManager.createHomePanel();

        // Adicionar o painel ao frame
        add(homeComponents.panel);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
        //setVisible(true);
    }
}
