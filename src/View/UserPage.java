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
import javax.swing.table.DefaultTableModel;
import Controller.TaskController;
import Model.Task;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserPage extends JPanel {
    private TaskController taskController;
    private String loggedInUser;
    private JTable taskTable;
    private DefaultTableModel tableModel;

    public UserPage(TaskController taskController) {
        this.taskController = taskController;
        this.loggedInUser = loggedInUser;
        initializeComponents();
        setupLayout();
        displayUserTasks();
    }

    private void initializeComponents() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Priority", "Deadline", "Status"}, 0);
        taskTable = new JTable(tableModel);

        JButton btnAddTask = new JButton("Add Task");
        JButton btnEditTask = new JButton("Edit Task");
        JButton btnDeleteTask = new JButton("Delete Task");
        JButton btnHome = new JButton("Home");

        btnAddTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        btnEditTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        btnDeleteTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateHome();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAddTask);
        buttonPanel.add(btnEditTask);
        buttonPanel.add(btnDeleteTask);
        buttonPanel.add(btnHome);

        JScrollPane scrollPane = new JScrollPane(taskTable);

        setLayout(new BorderLayout());
        add(new JLabel("User Dashboard - Tasks for " + loggedInUser), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupLayout() {
        // Additional setup if needed
    }

    private void displayUserTasks() {
        List<Task> tasks = taskController.getTasksForUser(loggedInUser);
        tableModel.setRowCount(0); // Clear existing rows

        for (Task task : tasks) {
            tableModel.addRow(new Object[]{
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getPriority(),
                task.getDeadline(),
                task.getStatus()
            });
        }
    }

    private void addTask() {
        String name = JOptionPane.showInputDialog("Enter Task Name:");
        String description = JOptionPane.showInputDialog("Enter Task Description:");
        String priority = JOptionPane.showInputDialog("Enter Task Priority (High, Medium, Low):");
        java.util.Date deadline = new java.util.Date(); // Simplified for demonstration purposes

        if (name != null && description != null && priority != null) {
            taskController.addTask(name, description, priority, deadline, "Pending", loggedInUser);
            displayUserTasks();
        } else {
            JOptionPane.showMessageDialog(this, "All fields are required.");
        }
    }

    private void editTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            int taskId = (int) tableModel.getValueAt(selectedRow, 0);
            String name = JOptionPane.showInputDialog("Edit Task Name:", tableModel.getValueAt(selectedRow, 1));
            String description = JOptionPane.showInputDialog("Edit Task Description:", tableModel.getValueAt(selectedRow, 2));
            String priority = JOptionPane.showInputDialog("Edit Task Priority (High, Medium, Low):", tableModel.getValueAt(selectedRow, 3));

            taskController.editTask(taskId, name, description, priority, new java.util.Date(), "Pending", loggedInUser);
            displayUserTasks();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to edit.");
        }
    }

    private void deleteTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            int taskId = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Delete Task", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskController.deleteTask(taskId);
                displayUserTasks();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    private void navigateHome() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        CardLayout cl = (CardLayout) topFrame.getContentPane().getLayout();
        cl.show(topFrame.getContentPane(), "LoginPanel");
    }
}