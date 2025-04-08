package org.example.src.views;

import org.example.src.components.DespesaManager;
import org.example.src.components.TransacoesScreen;
import org.example.src.models.RegistroGlobal;
import org.example.src.components.Styles;
import org.example.src.models.Transacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DespesaScreen extends JFrame implements ActionListener {
    // Componentes da tela
    private JComboBox<String> categoriaComboBox;
    private JTextField valorField;
    private JTextField motivoField;
    private JSpinner dataSpinner;
    private SpinnerDateModel dataModel;
    private JButton salvarButton;
    private JButton voltarButton;

    public DespesaScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Cadastro de Despesa");

        // Criar painel de despesa usando o DespesaManager
        DespesaManager.DespesaPanelComponents despesaComponents = DespesaManager.createDespesaPanel();

        // Armazenar referências aos componentes para uso posterior
        categoriaComboBox = despesaComponents.categoriaComboBox;
        valorField = despesaComponents.valorField;
        motivoField = despesaComponents.motivoField;
        dataSpinner = despesaComponents.dataSpinner;
        dataModel = despesaComponents.dataModel;
        salvarButton = despesaComponents.salvarButton;
        voltarButton = despesaComponents.voltarButton;

        // Registrar este objeto como listener dos botões
        salvarButton.addActionListener(this);
        voltarButton.addActionListener(this);

        // Adicionar o painel ao frame
        add(despesaComponents.panel);

        // Centralizar, ajustar tamanho e mostrar
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
        String motivoTexto = motivoField.getText().trim();

        // Validar se todos os campos foram preenchidos
        if (valorTexto.isEmpty() || motivoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos devem ser preenchidos.",
                    "Dados incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar formato do valor
        double valorNumerico;
        try {
            valorNumerico = Double.parseDouble(valorTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Insira um valor numérico válido.",
                    "Formato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obter data do spinner
        java.util.Date data = dataModel.getDate();

        // Criar objeto Transacao
        Transacao novaDespesa = new Transacao(
                data,
                motivoTexto,
                categoriaSelecionada,
                valorNumerico,
                Transacao.TipoTransacao.DESPESA
        );

        // Adicionar ao registro global
        RegistroGlobal.adicionar(novaDespesa);

        JOptionPane.showMessageDialog(this,
                "Despesa salva com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Voltar para a tela de transições
        dispose();
        new TransacoesScreen().setVisible(true);
    }

    private void handleVoltar() {
        dispose();
        new TransacoesScreen().setVisible(true);
    }
}