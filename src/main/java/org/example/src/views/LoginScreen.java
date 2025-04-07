package org.example.src.views;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.src.components.LoginManager;
import org.example.src.components.Styles;
import org.example.src.models.Usuario;

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
        LoginManager.LoginPanelComponents loginComponents = LoginManager.createLoginPanel();

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

        if (Usuario.validateUser(username, password)) {
            // Obtém o usuário do mapa de usuários
            // Você precisaria adicionar este método na classe Usuario
            Usuario usuarioLogado = Usuario.getUser(username);

            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + usuarioLogado.getName() + "!",
                    "Login bem-sucedido", JOptionPane.INFORMATION_MESSAGE);

            // Abrir a tela principal passando o usuário
            HomeScreen homeScreen = new HomeScreen(usuarioLogado);
            homeScreen.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    // Método para lidar com o registro
    private void handleRegister() {
        dispose(); // Fechar tela atual
        SignUpScreen signUpScreen = new SignUpScreen();
        signUpScreen.setVisible(true);
    }

    // Método para validar as credenciais
    private boolean validateLogin(String username, String password) {
        return VALID_USERNAME.equals(username) &&
                VALID_PASSWORD.equals(password);
    }
}