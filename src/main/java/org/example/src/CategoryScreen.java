package org.example.src;

import org.example.src.components.CategoryManager;

import javax.swing.*;

public class CategoryScreen {

    public static void main(String[] args){
        // Cria a janela principal da tela
        JFrame frame = new JFrame("Gerenciador de Categorias");

        // Utiliza os componentes definidos na CategoryManager
        CategoryManager.CategoryPanelComponents components = new CategoryManager.CategoryPanelComponents();

        // Define o conteúdo da janela como painel de categorias
        frame.setContentPane(components.panel);

        // Configurações da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();   // Ajusta automaticamente o tamanho da janela ao tamanho dos componentes
        frame.setLocationRelativeTo(null);  // Centraliza a janela na tela
        frame.setVisible(true); // Exibe a janela
    }
}
