package org.example.src.components;

import javax.swing.*;

public class CategoryManager extends JPanel {

    // Declara uma classe para definir os componentes do painel de categoria
    public static class CategoryPanelComponents {
        public final JPanel panel;
        public final JLabel label;
        public final JTextField textField;
        public final JButton addButton;
        public final JButton editButton;
        public final JButton removeButton;
        public final JList<String> categoryList;

        // Construtor dos itens
        public CategoryPanelComponents() {
            panel = new JPanel();
            label = new JLabel("Nome da Categoria:");
            textField = new JTextField(20);
            addButton = new JButton("Adicionar");
            editButton = new JButton("Editar");
            removeButton = new JButton("Remover");
            categoryList = new JList<>(new DefaultListModel<>());

            // Layout simples com BoxLayout
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Adiciona componentes ao painel
            panel.add(label);
            panel.add(textField);
            panel.add(addButton);
            panel.add(editButton);
            panel.add(removeButton);
            panel.add(new JScrollPane(categoryList));
        }
    }
}