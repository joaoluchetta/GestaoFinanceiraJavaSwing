package org.example.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.src.components.PanelManager;
import org.example.src.components.Styles;

public class LoginScreen extends JFrame implements ActionListener {

    // Credenciais válidas (numa aplicação real, estariam num banco de dados)
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    // Componentes da tela de login
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setTitle("SaveMoney");

        // Criar painel de login usando o PanelManager
        PanelManager.LoginPanelComponents loginComponents = PanelManager.createLoginPanel();

        // Armazenar referências aos componentes para uso posterior
        usernameField = loginComponents.usernameField;
        passwordField = loginComponents.passwordField;
        loginButton = loginComponents.loginButton;
        registerButton = loginComponents.registerButton;

        // Registrar este objeto como listener dos botões
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Adicionar o painel ao frame
        add(loginComponents.panel);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    // Implementação do método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == registerButton) {
            handleRegister();
        }
    }

    // Método para lidar com o login
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (validateLogin(username, password)) {
            JOptionPane.showMessageDialog(this,
                    "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Aqui você abriria a próxima tela da aplicação
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.setVisible(true);
            dispose(); //Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    // Método para lidar com o registro
    private void handleRegister() {
        JOptionPane.showMessageDialog(this,
                "Funcionalidade de registro ainda não implementada!",
                "Em desenvolvimento", JOptionPane.INFORMATION_MESSAGE);

        // Aqui você abriria uma tela de registro
        // RegisterScreen registerScreen = new RegisterScreen();
        // registerScreen.setVisible(true);
    }

    // Método para validar as credenciais
    private boolean validateLogin(String username, String password) {
        return VALID_USERNAME.equals(username) &&
                VALID_PASSWORD.equals(password);
    }
}