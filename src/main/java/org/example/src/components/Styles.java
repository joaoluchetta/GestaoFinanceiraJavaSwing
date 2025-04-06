package org.example.src.components;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Styles {
    // Constantes para dimensões padrão
    public static final int DEFAULT_TEXT_WIDTH = 200;
    public static final int DEFAULT_TEXT_HEIGHT = 30;
    public static final int DEFAULT_BUTTON_WIDTH = 150;
    public static final int DEFAULT_BUTTON_HEIGHT = 30;
    public static final int DEFAULT_TABLE_WIDTH = 600;
    public static final int DEFAULT_TABLE_HEIGHT = 300;
    public static final int DEFAULT_TABLE_ROW_HEIGHT = 20;

    // Aplicando tamanho padrão a componentes de texto
    public static void setDefaultTextFieldSize(JComponent textComponent) {
        textComponent.setPreferredSize(new Dimension(DEFAULT_TEXT_WIDTH, DEFAULT_TEXT_HEIGHT));
    }

    // Aplicando tamanho padrão para tabela
    public static void setDefaultLabelSize(JComponent labelComponent) {
        labelComponent.setPreferredSize(new Dimension(DEFAULT_TEXT_WIDTH, DEFAULT_TEXT_HEIGHT));
    }
    public static void setTableHeaderStyle(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(58, 124, 165)); // Azul
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBorder(BorderFactory.createEtchedBorder());
        header.setPreferredSize(new Dimension(0, 30)); // Altura do cabeçalho
    }

    // Aplicando tamanho padrão para botões
    public static void setDefaultButtonSize(JButton button) {
        button.setPreferredSize(new Dimension(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(Short.MAX_VALUE, DEFAULT_BUTTON_HEIGHT));
    }

    public static void setDefaultTableSize(JTable table) {
        table.setPreferredScrollableViewportSize(new Dimension(DEFAULT_TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
        table.setRowHeight(DEFAULT_TABLE_ROW_HEIGHT);
    }

    // Centralizar janelas na tela
    public static void centerOnScreen(Window window) {
        window.setLocationRelativeTo(null);
    }

}