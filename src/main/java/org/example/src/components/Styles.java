package org.example.src.components;
import javax.swing.*;
import java.awt.*;

public class Styles {
    // Constantes para dimensões padrão
    public static final int DEFAULT_TEXT_WIDTH = 200;
    public static final int DEFAULT_TEXT_HEIGHT = 30;
    public static final int DEFAULT_BUTTON_WIDTH = 150;
    public static final int DEFAULT_BUTTON_HEIGHT = 30;

    // Método para aplicar tamanho padrão a componentes de texto
    public static void setDefaultTextFieldSize(JComponent textComponent) {
        textComponent.setPreferredSize(new Dimension(DEFAULT_TEXT_WIDTH, DEFAULT_TEXT_HEIGHT));
    }

    // Método para aplicar tamanho padrão a botões
    public static void setDefaultButtonSize(JButton button) {
        button.setPreferredSize(new Dimension(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT));
    }

    // Método para centralizar janelas na tela
    public static void centerOnScreen(Window window) {
        window.setLocationRelativeTo(null);
    }

}