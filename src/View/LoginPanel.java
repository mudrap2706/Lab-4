//Add text fields and a button for login.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author mudra
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.TaskController;

public class LoginPanel extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private MainFrame mainFrame;
    private TaskController taskController;

    public LoginPanel(MainFrame mainFrame, TaskController taskController) {
        this.mainFrame = mainFrame;
        this.taskController = taskController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("TASK MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        JButton btnLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnLogin, gbc);

        JButton btnSignUp = new JButton("Sign Up");
        gbc.gridx = 1;
        add(btnSignUp, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                String role = taskController.authenticateUser(username, password);
                if (role != null) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login successful as " + role);
                    if (role.equalsIgnoreCase("Admin")) {
                        mainFrame.showAdminPanel();
                    } else {
                        mainFrame.showUserPanel();
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid username or password.");
                }
            }
        });

        btnSignUp.addActionListener(e -> mainFrame.showSignUpPanel());
    }
}