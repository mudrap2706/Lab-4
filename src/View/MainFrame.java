//Implement navigation between different views using CardLayout.
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
import Controller.TaskController;
import Model.TaskManager;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private TaskController taskController;

    public MainFrame() {
        TaskManager taskManager = new TaskManager();
        taskController = new TaskController(taskManager);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        setContentPane(mainPanel);

        LoginPanel loginPanel = new LoginPanel(this, taskController);
        SignUp signUpPanel = new SignUp(this, taskController);
        AdminPage adminPage = new AdminPage(taskController);
        UserPage userPage = new UserPage(taskController);

        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(signUpPanel, "SignUpPanel");
        mainPanel.add(adminPage, "AdminPanel");
        mainPanel.add(userPage, "UserPanel");

        setTitle("Task Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showSignUpPanel() {
        cardLayout.show(mainPanel, "SignUpPanel");
    }

    public void showAdminPanel() {
        cardLayout.show(mainPanel, "AdminPanel");
    }

    public void showUserPanel() {
        cardLayout.show(mainPanel, "UserPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    
    }
}