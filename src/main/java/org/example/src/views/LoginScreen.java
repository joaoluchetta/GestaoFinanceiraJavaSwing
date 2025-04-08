package org.example.src.views;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.src.components.LoginManager;
import org.example.src.components.Styles;
import org.example.src.models.Usuario;

public class LoginScreen extends JFrame implements ActionListener {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setTitle("SaveMoney");
        
        LoginManager.LoginPanelComponents loginComponents = LoginManager.createLoginPanel();
        
        usernameField = loginComponents.usernameField;
        passwordField = loginComponents.passwordField;
        loginButton = loginComponents.loginButton;
        registerButton = loginComponents.registerButton;
        
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        
        add(loginComponents.panel);
        
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == registerButton) {
            handleRegister();
        }
    }
    
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (Usuario.validateUser(username, password)) {
            Usuario usuarioLogado = Usuario.getUser(username);

            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + usuarioLogado.getName() + "!",
                    "Login bem-sucedido", JOptionPane.INFORMATION_MESSAGE);
            
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
    
    private void handleRegister() {
        dispose(); // Fechar tela atual
        SignUpScreen signUpScreen = new SignUpScreen();
        signUpScreen.setVisible(true);
    }
    
}