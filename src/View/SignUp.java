//Setup sign-up fields for new users.
//Add basic form validation in the login and sign-up panels.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author mudra
 */
import Controller.TaskController;
import Model.TaskManager;
import javax.swing.*;
import java.awt.*;

public class SignUp extends JPanel {
    private TaskController taskController;
    private JTextField txtUsername, txtPassword, txtRole;
    private JButton btnSubmit, btnBackToLogin;

    public SignUp(MainFrame aThis, TaskController taskController) {
        this.taskController = taskController;
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtRole = new JTextField(15);

        btnSubmit = new JButton("Submit");
        btnBackToLogin = new JButton("Back to Login");

        btnSubmit.addActionListener(e -> handleSignUp());
        btnBackToLogin.addActionListener(e -> navigateToLogin());
    }

    private void setupLayout() {
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Username:"));
        add(txtUsername);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(new JLabel("Role (Admin/User):"));
        add(txtRole);
        add(btnSubmit);
        add(btnBackToLogin);
    }

    private void handleSignUp() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
        } else {
            taskController.registerUser(username, password, role);
            JOptionPane.showMessageDialog(this, "User registered successfully.");
        }
    }

    private void navigateToLogin() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        CardLayout cl = (CardLayout) topFrame.getContentPane().getLayout();
        cl.show(topFrame.getContentPane(), "LoginPanel");
    }
}
