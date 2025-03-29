package org.example.src.components;
import javax.swing.*;
import java.awt.*;

public class PanelManager extends JPanel {

    // Método para criar um painel de login (agora retorna o painel e os componentes)
    public static LoginPanelComponents createLoginPanel() {
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(3, 2, 10, 10));

        //Campos para o usuario realizar o login
        JLabel labelUser = new JLabel("Usuário:");
        JTextField textUserName = new JTextField();
        Styles.setDefaultTextFieldSize(textUserName);

        JLabel labelPassword = new JLabel("Senha:");
        JPasswordField textUserPassword = new JPasswordField();
        Styles.setDefaultTextFieldSize(textUserPassword);

        JButton buttonLogin = new JButton("Login");
        Styles.setDefaultButtonSize(buttonLogin);
        JButton buttonRegister = new JButton("Register");
        Styles.setDefaultButtonSize(buttonRegister);

        panelLogin.add(labelUser);
        panelLogin.add(textUserName);
        panelLogin.add(labelPassword);
        panelLogin.add(textUserPassword);
        panelLogin.add(buttonLogin);
        panelLogin.add(buttonRegister);

        // Criando um objeto para retornar todos os componentes necessários
        return new LoginPanelComponents(
                panelLogin,
                textUserName,
                textUserPassword,
                buttonLogin,
                buttonRegister
        );
    }

    // Classe interna para armazenar os componentes do painel de login
    public static class LoginPanelComponents {
        public final JPanel panel;
        public final JTextField usernameField;
        public final JPasswordField passwordField;
        public final JButton loginButton;
        public final JButton registerButton;

        public LoginPanelComponents(
                JPanel panel,
                JTextField usernameField,
                JPasswordField passwordField,
                JButton loginButton,
                JButton registerButton) {
            this.panel = panel;
            this.usernameField = usernameField;
            this.passwordField = passwordField;
            this.loginButton = loginButton;
            this.registerButton = registerButton;
        }
    }

    //Método para criar o painel principal do sistema
    public static HomePanelComponents createHomePanel() {
        JPanel panelHome = new JPanel();
        panelHome.setLayout(new GridLayout(3, 3, 10, 10));

        JButton buttonTela1 = new JButton("TELA 1");
        Styles.setDefaultButtonSize(buttonTela1);
        JButton buttonTela2 = new JButton("TELA 2");
        Styles.setDefaultButtonSize(buttonTela2);
        JButton buttonTela3 = new JButton("TELA 3");
        Styles.setDefaultButtonSize(buttonTela3);


        panelHome.add(buttonTela1);
        panelHome.add(buttonTela2);
        panelHome.add(buttonTela3);

        return new HomePanelComponents(
                panelHome,
                buttonTela1,
                buttonTela2,
                buttonTela3
        );
    }

    public static class HomePanelComponents {
        public final JPanel panel;
        public final JButton tela1Button;
        public final JButton tela2Button;
        public final JButton tela3Button;

        public HomePanelComponents(
                JPanel panel,
                JButton tela1Button,
                JButton tela2Button,
                JButton tela3Button){
            this.panel = panel;
            this.tela1Button = tela1Button;
            this.tela2Button = tela2Button;
            this.tela3Button = tela3Button;
        }
    }
}
