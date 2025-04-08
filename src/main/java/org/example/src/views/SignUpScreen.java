package org.example.src.views;
import org.example.src.components.SignUpManager;
import org.example.src.components.Styles;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.src.models.Usuario;

public class SignUpScreen extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public SignUpScreen() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setTitle("SaveMoney");

        // Criar painel de login usando o PanelManager
        SignUpManager.SignUpPanelComponents signUpComponents = SignUpManager.createSignUpPanel();

        nameField = signUpComponents.textNameField;
        usernameField = signUpComponents.usernameField;
        passwordField = signUpComponents.passwordField;
        registerButton = signUpComponents.registerButton;
        backButton = signUpComponents.backButton;

        // Registrar este objeto como listener dos botões
        registerButton.addActionListener(this);
        backButton.addActionListener(this);

        add(signUpComponents.panelSignUp);

        // Centralizar, ajustar tamanho e mostrar
        pack();
        Styles.centerOnScreen(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            handleRegistration();
        } else if (e.getSource() == backButton) {
            handleBack();
        }
    }

    private void handleRegistration() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos são obrigatórios!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (Usuario.userExists(username)) {
            JOptionPane.showMessageDialog(this,
                    "Este nome de usuário já está em uso. Por favor, escolha outro.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            usernameField.requestFocus();
            return;
        }

        if (Usuario.addUser(username, password, name)) {
            JOptionPane.showMessageDialog(this,
                    "Usuário cadastrado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            new LoginScreen();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar usuário. Por favor, tente novamente.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleBack() {
        dispose();
        new LoginScreen();
    }
}
