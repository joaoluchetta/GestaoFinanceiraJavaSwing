package org.example.src.components;

import javax.swing.*;
import java.awt.*;

public class SignUpManager extends JPanel {

    public static SignUpPanelComponents createSignUpPanel() {
        JPanel panelSignUp = new JPanel();
        panelSignUp.setLayout(new GridLayout(4, 2, 10, 10));

        //Campos para o usuario realizar o cadatro
        JLabel labelName = new JLabel("Nome:");
        JTextField textName = new JTextField();
        Styles.setDefaultTextFieldSize(textName);

        JLabel labelUser = new JLabel("Usuário:");
        JTextField textUsername = new JTextField();
        Styles.setDefaultTextFieldSize(textUsername);

        JLabel labelPassword = new JLabel("Senha:");
        JPasswordField textUserPassword = new JPasswordField();
        Styles.setDefaultTextFieldSize(textUserPassword);

        JButton buttonRegister = new JButton("Registrar");
        Styles.setDefaultButtonSize(buttonRegister);
        JButton buttonBack = new JButton("Voltar");
        Styles.setDefaultButtonSize(buttonBack);

        panelSignUp.add(labelName);
        panelSignUp.add(textName);
        panelSignUp.add(labelUser);
        panelSignUp.add(textUsername);
        panelSignUp.add(labelPassword);
        panelSignUp.add(textUserPassword);

        panelSignUp.add(buttonRegister);
        panelSignUp.add(buttonBack);

        // Criando um objeto para retornar todos os componentes necessários
        return new SignUpPanelComponents(
                panelSignUp,
                textName,
                textUsername,
                textUserPassword,
                buttonRegister,
                buttonBack
        );
    }

    // Classe interna para armazenar os componentes do painel de login
    public static class SignUpPanelComponents {
        public final JPanel panelSignUp;
        public final JTextField textNameField;
        public final JTextField usernameField;
        public final JPasswordField passwordField;
        public final JButton registerButton;
        public final JButton backButton;

        public SignUpPanelComponents(
                JPanel panelSignUp,
                JTextField textNameField,
                JTextField usernameField,
                JPasswordField passwordField,
                JButton registerButton,
                JButton backButton) {
            this.panelSignUp = panelSignUp;
            this.textNameField = textNameField;
            this.usernameField = usernameField;
            this.passwordField = passwordField;
            this.registerButton = registerButton;
            this.backButton = backButton;}
    }
}
