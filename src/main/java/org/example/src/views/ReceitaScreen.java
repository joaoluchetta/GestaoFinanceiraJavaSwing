package org.example.src.views;
import org.example.src.components.ReceitaManager;
import org.example.src.components.TransacoesScreen;
import org.example.src.models.RegistroGlobal;
import org.example.src.components.Styles;
import org.example.src.models.Transacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceitaScreen extends JFrame implements ActionListener {
    private JComboBox<String> categoriaComboBox;
    private JTextField valorField;
    private JTextField descricaoField;
    private JSpinner dataSpinner;
    private SpinnerDateModel dataModel;
    private JButton salvarButton;
    private JButton voltarButton;

    public ReceitaScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Cadastro de Receita");
        
        ReceitaManager.ReceitaPanelComponents receitaComponents = ReceitaManager.createReceitaPanel();
        
        categoriaComboBox = receitaComponents.categoriaComboBox;
        valorField = receitaComponents.valorField;
        descricaoField = receitaComponents.descricaoField;
        dataSpinner = receitaComponents.dataSpinner;
        dataModel = receitaComponents.dataModel;
        salvarButton = receitaComponents.salvarButton;
        voltarButton = receitaComponents.voltarButton;
        
        salvarButton.addActionListener(this);
        voltarButton.addActionListener(this);
        
        add(receitaComponents.panel);
        
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salvarButton) {
            handleSalvar();
        } else if (e.getSource() == voltarButton) {
            handleVoltar();
        }
    }

    private void handleSalvar() {
        String categoriaSelecionada = categoriaComboBox.getSelectedItem().toString();
        String valorTexto = valorField.getText().trim();
        String descricaoTexto = descricaoField.getText().trim();
        
        if (valorTexto.isEmpty() || descricaoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos devem ser preenchidos.",
                    "Dados incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Valida formato do valor
        double valorNumerico;
        try {
            valorNumerico = Double.parseDouble(valorTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Insira um valor numérico válido.",
                    "Formato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        java.util.Date data = dataModel.getDate();
        Transacao novaReceita = new Transacao(
                data,
                descricaoTexto,
                categoriaSelecionada,
                valorNumerico,
                Transacao.TipoTransacao.RECEITA
        );
        
        RegistroGlobal.adicionar(novaReceita);
        JOptionPane.showMessageDialog(this,
                "Receita salva com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
        new TransacoesScreen().setVisible(true);
    }

    private void handleVoltar() {
        dispose();
        new TransacoesScreen().setVisible(true);
    }
}