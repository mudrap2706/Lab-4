//Set up basic table layout for displaying tasks.
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
import Model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPage extends JPanel {

    private TaskController taskController;
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JButton btnAddTask, btnEditTask, btnDeleteTask, btnBack;

    public AdminPage(TaskController taskController) {
        this.taskController = taskController;
        initializeComponents();
        setupLayout();
        displayAllTasks(taskController.getAllTasks());
    }

    private void initializeComponents() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Priority", "Deadline", "Status", "Assigned User"}, 0);
        taskTable = new JTable(tableModel);

        btnAddTask = new JButton("Add Task");
        btnEditTask = new JButton("Edit Task");
        btnDeleteTask = new JButton("Delete Task");
        btnBack = new JButton("Back");

        btnAddTask.addActionListener(e -> addTask());
        btnEditTask.addActionListener(e -> editTask());
        btnDeleteTask.addActionListener(e -> deleteTask());
        btnBack.addActionListener(e -> navigateBack());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAddTask);
        buttonPanel.add(btnEditTask);
        buttonPanel.add(btnDeleteTask);
        buttonPanel.add(btnBack);

        JScrollPane scrollPane = new JScrollPane(taskTable);

        add(new JLabel("Admin Dashboard - Manage All Tasks"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void displayAllTasks(List<Task> tasks) {
        tableModel.setRowCount(0);

        for (Task task : tasks) {
            tableModel.addRow(new Object[]{
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getPriority(),
                task.getDeadline(),
                task.getStatus(),
                task.getAssignedUser()
            });
        }
    }

    private void addTask() {
        String name = JOptionPane.showInputDialog("Enter Task Name:");
        String description = JOptionPane.showInputDialog("Enter Task Description:");
        String priority = JOptionPane.showInputDialog("Enter Task Priority (High, Medium, Low):");
        String assignedUser = JOptionPane.showInputDialog("Enter Assigned User:");
        java.util.Date deadline = new java.util.Date(); 

        if (name != null && description != null && priority != null && assignedUser != null) {
            taskController.addTask(name, description, priority, deadline, "Pending", assignedUser);
            displayAllTasks(taskController.getAllTasks());
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
            java.util.Date deadline = new java.util.Date(); 
            String status = JOptionPane.showInputDialog("Edit Task Status:", tableModel.getValueAt(selectedRow, 5));
            String assignedUser = JOptionPane.showInputDialog("Edit Assigned User:", tableModel.getValueAt(selectedRow, 6));

            taskController.editTask(taskId, name, description, priority, deadline, status, assignedUser);
            displayAllTasks(taskController.getAllTasks());
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
                displayAllTasks(taskController.getAllTasks());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
        }
    }

    private void navigateBack() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        CardLayout cl = (CardLayout) topFrame.getContentPane().getLayout();
        cl.show(topFrame.getContentPane(), "LoginPanel");
    }
}
