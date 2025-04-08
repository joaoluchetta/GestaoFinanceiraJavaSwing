package org.example.src.components;

import javax.swing.*;
import java.awt.*;
import org.example.src.models.Categoria;

public class DespesaManager extends JPanel {

    public static DespesaPanelComponents createDespesaPanel() {
        JPanel panelDespesa = new JPanel();
        panelDespesa.setLayout(new GridLayout(6, 1, 5, 5));
        
        String[] categoriaString = Categoria.getNomesCategorias(Categoria.TipoCategoria.DESPESA);
        
        JComboBox<String> categoria = new JComboBox<>(categoriaString);
        JTextField valor = new JTextField();
        JTextField motivo = new JTextField();
        
        SpinnerDateModel modelData = new SpinnerDateModel();
        JSpinner spinnerData = new JSpinner(modelData);
        spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
        
        JButton salvarButton = new JButton("Salvar");
        Styles.setDefaultButtonSize(salvarButton);
        JButton voltarButton = new JButton("Voltar para o menu");
        Styles.setDefaultButtonSize(voltarButton);
        
        panelDespesa.add(new JLabel("Categoria:"));
        panelDespesa.add(categoria);
        panelDespesa.add(new JLabel("Valor (R$):"));
        panelDespesa.add(valor);
        panelDespesa.add(new JLabel("Motivo:"));
        panelDespesa.add(motivo);
        panelDespesa.add(new JLabel("Data:"));
        panelDespesa.add(spinnerData);
        panelDespesa.add(salvarButton);
        panelDespesa.add(voltarButton);
        
        return new DespesaPanelComponents(
                panelDespesa,
                categoria,
                valor,
                motivo,
                spinnerData,
                modelData,
                salvarButton,
                voltarButton
        );
    }
    
    public static class DespesaPanelComponents {
        public final JPanel panel;
        public final JComboBox<String> categoriaComboBox;
        public final JTextField valorField;
        public final JTextField motivoField;
        public final JSpinner dataSpinner;
        public final SpinnerDateModel dataModel;
        public final JButton salvarButton;
        public final JButton voltarButton;

        public DespesaPanelComponents(
                JPanel panel,
                JComboBox<String> categoriaComboBox,
                JTextField valorField,
                JTextField motivoField,
                JSpinner dataSpinner,
                SpinnerDateModel dataModel,
                JButton salvarButton,
                JButton voltarButton) {
            this.panel = panel;
            this.categoriaComboBox = categoriaComboBox;
            this.valorField = valorField;
            this.motivoField = motivoField;
            this.dataSpinner = dataSpinner;
            this.dataModel = dataModel;
            this.salvarButton = salvarButton;
            this.voltarButton = voltarButton;
        }
    }
}