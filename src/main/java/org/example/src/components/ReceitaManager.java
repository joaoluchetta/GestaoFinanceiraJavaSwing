package org.example.src.components;

import javax.swing.*;
import java.awt.*;
import org.example.src.models.Categoria;

public class ReceitaManager extends JPanel {

    public static ReceitaPanelComponents createReceitaPanel() {
        JPanel panelReceita = new JPanel();
        panelReceita.setLayout(new GridLayout(6, 1, 5, 5));

        // Categorias para receitas
        String[] categoriaString = Categoria.getNomesCategorias(Categoria.TipoCategoria.RECEITA);

        // Componentes do painel
        JComboBox<String> categoria = new JComboBox<>(categoriaString);
        JTextField valor = new JTextField();
        JTextField descricao = new JTextField();

        // Componente para data
        SpinnerDateModel modelData = new SpinnerDateModel();
        JSpinner spinnerData = new JSpinner(modelData);
        spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));

        // Botões
        JButton salvarButton = new JButton("Salvar");
        Styles.setDefaultButtonSize(salvarButton);
        JButton voltarButton = new JButton("Voltar para o menu");
        Styles.setDefaultButtonSize(voltarButton);

        // Adicionar componentes ao painel
        panelReceita.add(new JLabel("Categoria:"));
        panelReceita.add(categoria);
        panelReceita.add(new JLabel("Valor (R$):"));
        panelReceita.add(valor);
        panelReceita.add(new JLabel("Descrição:"));
        panelReceita.add(descricao);
        panelReceita.add(new JLabel("Data:"));
        panelReceita.add(spinnerData);
        panelReceita.add(salvarButton);
        panelReceita.add(voltarButton);

        // Criar e retornar o objeto de componentes
        return new ReceitaPanelComponents(
                panelReceita,
                categoria,
                valor,
                descricao,
                spinnerData,
                modelData,
                salvarButton,
                voltarButton
        );
    }

    // Classe interna para armazenar os componentes do painel
    public static class ReceitaPanelComponents {
        public final JPanel panel;
        public final JComboBox<String> categoriaComboBox;
        public final JTextField valorField;
        public final JTextField descricaoField;
        public final JSpinner dataSpinner;
        public final SpinnerDateModel dataModel;
        public final JButton salvarButton;
        public final JButton voltarButton;

        public ReceitaPanelComponents(
                JPanel panel,
                JComboBox<String> categoriaComboBox,
                JTextField valorField,
                JTextField descricaoField,
                JSpinner dataSpinner,
                SpinnerDateModel dataModel,
                JButton salvarButton,
                JButton voltarButton) {
            this.panel = panel;
            this.categoriaComboBox = categoriaComboBox;
            this.valorField = valorField;
            this.descricaoField = descricaoField;
            this.dataSpinner = dataSpinner;
            this.dataModel = dataModel;
            this.salvarButton = salvarButton;
            this.voltarButton = voltarButton;
        }
    }
}