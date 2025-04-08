package org.example.src.components;

import org.example.src.models.Categoria;

import javax.swing.*;
import java.awt.*;

public class CategoriaManager extends JPanel {

    public static CategoriaPanelComponents createCategoriaPanel() {
        JPanel panelCategoria = new JPanel();
        panelCategoria.setLayout(new BorderLayout(10, 10));

        // Painel para adicionar nova categoria
        JPanel panelAdicionar = new JPanel(new GridLayout(4, 2, 10, 10));
        panelAdicionar.setBorder(BorderFactory.createTitledBorder("Adicionar Categoria"));

        // Campos para cadastro
        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField(20);
        Styles.setDefaultTextFieldSize(textNome);

        JLabel labelTipo = new JLabel("Tipo:");
        String[] tiposCategoria = {"Receita", "Despesa"};
        JComboBox<String> comboTipo = new JComboBox<>(tiposCategoria);

        JLabel labelDescricao = new JLabel("Descrição:");
        JTextField textDescricao = new JTextField(30);
        Styles.setDefaultTextFieldSize(textDescricao);

        // Botões
        JButton buttonAdicionar = new JButton("Adicionar");
        Styles.setDefaultButtonSize(buttonAdicionar);

        JButton buttonEditar = new JButton("Editar");
        Styles.setDefaultButtonSize(buttonEditar);

        JButton buttonRemover = new JButton("Remover");
        Styles.setDefaultButtonSize(buttonRemover);

        JButton buttonVoltar = new JButton("Voltar");
        Styles.setDefaultButtonSize(buttonVoltar);

        // Adicionar componentes ao painel de cadastro
        panelAdicionar.add(labelNome);
        panelAdicionar.add(textNome);
        panelAdicionar.add(labelTipo);
        panelAdicionar.add(comboTipo);
        panelAdicionar.add(labelDescricao);
        panelAdicionar.add(textDescricao);
        panelAdicionar.add(buttonAdicionar);
        panelAdicionar.add(buttonEditar);

        // Painel com as listas de categorias
        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelListas.setBorder(BorderFactory.createTitledBorder("Categorias existentes"));

        // Lista de receitas
        JPanel panelReceitas = new JPanel(new BorderLayout());
        panelReceitas.setBorder(BorderFactory.createTitledBorder("Receitas"));
        DefaultListModel<String> modelReceitas = new DefaultListModel<>();
        for (Categoria c : Categoria.listarReceitas()) {
            modelReceitas.addElement(c.getNome());
        }
        JList<String> listaReceitas = new JList<>(modelReceitas);
        JScrollPane scrollReceitas = new JScrollPane(listaReceitas);
        panelReceitas.add(scrollReceitas, BorderLayout.CENTER);

        // Lista de despesas
        JPanel panelDespesas = new JPanel(new BorderLayout());
        panelDespesas.setBorder(BorderFactory.createTitledBorder("Despesas"));
        DefaultListModel<String> modelDespesas = new DefaultListModel<>();
        for (Categoria c : Categoria.listarDespesas()) {
            modelDespesas.addElement(c.getNome());
        }
        JList<String> listaDespesas = new JList<>(modelDespesas);
        JScrollPane scrollDespesas = new JScrollPane(listaDespesas);
        panelDespesas.add(scrollDespesas, BorderLayout.CENTER);

        // Adicionar as listas ao painel
        panelListas.add(panelReceitas);
        panelListas.add(panelDespesas);

        // Painel de botões inferiores
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(buttonRemover);
        panelBotoes.add(buttonVoltar);

        // Adicionar tudo ao painel principal
        panelCategoria.add(panelAdicionar, BorderLayout.NORTH);
        panelCategoria.add(panelListas, BorderLayout.CENTER);
        panelCategoria.add(panelBotoes, BorderLayout.SOUTH);

        // Retornar os componentes
        return new CategoriaPanelComponents(
                panelCategoria,
                textNome,
                comboTipo,
                textDescricao,
                listaReceitas,
                listaDespesas,
                modelReceitas,
                modelDespesas,
                buttonAdicionar,
                buttonEditar,
                buttonRemover,
                buttonVoltar
        );
    }

    public static class CategoriaPanelComponents {
        public final JPanel panel;
        public final JTextField nomeField;
        public final JComboBox<String> tipoCombo;
        public final JTextField descricaoField;
        public final JList<String> listaReceitas;
        public final JList<String> listaDespesas;
        public final DefaultListModel<String> modelReceitas;
        public final DefaultListModel<String> modelDespesas;
        public final JButton adicionarButton;
        public final JButton editarButton;
        public final JButton removerButton;
        public final JButton voltarButton;

        public CategoriaPanelComponents(
                JPanel panel,
                JTextField nomeField,
                JComboBox<String> tipoCombo,
                JTextField descricaoField,
                JList<String> listaReceitas,
                JList<String> listaDespesas,
                DefaultListModel<String> modelReceitas,
                DefaultListModel<String> modelDespesas,
                JButton adicionarButton,
                JButton editarButton,
                JButton removerButton,
                JButton voltarButton) {
            this.panel = panel;
            this.nomeField = nomeField;
            this.tipoCombo = tipoCombo;
            this.descricaoField = descricaoField;
            this.listaReceitas = listaReceitas;
            this.listaDespesas = listaDespesas;
            this.modelReceitas = modelReceitas;
            this.modelDespesas = modelDespesas;
            this.adicionarButton = adicionarButton;
            this.editarButton = editarButton;
            this.removerButton = removerButton;
            this.voltarButton = voltarButton;
        }
    }
}