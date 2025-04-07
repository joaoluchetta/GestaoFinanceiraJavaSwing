package org.example.src.views;

import org.example.src.components.HomeManager;
import org.example.src.components.Styles;
import org.example.src.models.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener {

    private JButton sairButton;
    private Usuario usuarioLogado;

    public HomeScreen(Usuario usuario) {
        // Verifica se o usuário não é nulo
        if (usuario == null) {
            throw new IllegalArgumentException("Não é possível criar HomeScreen sem um usuário");
        }
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Resumo Financeiro - " + usuario.getName() + " - (ID: " + usuarioLogado.getId() + ")");

        // Criar painel principal  usando o PanelManager
        HomeManager.HomePanelComponents homeComponents = HomeManager.createHomePanel();

        // Armazenar referências aos componentes para uso posterior
        sairButton = homeComponents.sairButton;

        //Registra este objeto como listener dos botões
        sairButton.addActionListener(this);


        // Adicionar o painel ao frame
        add(homeComponents.panelHome);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
    }

    // Implementação do método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sairButton) {
            handleSair();
        }
    }

    // Método para lidar com o login
    private void handleSair() {
        int answer = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
            dispose();
            new LoginScreen(); //volta para a telad e login
        }

    }
}
