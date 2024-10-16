//Implement a method to add tasks to a list.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author mudra
 */
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private List<User> users;

    public TaskManager() {
        tasks = new ArrayList<>();
        users = new ArrayList<>();
    }

    public boolean addUser(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // User already exists
            }
        }
        users.add(new User(username, password, role));
        return true;
    }

    public String authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getRole();
            }
        }
        return null; // Authentication failed
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getTasksForUser(String username) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssignedUser().equals(username)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    public void addTask(String name, String description, String priority, java.util.Date deadline, String status, String assignedUser) {
        int newId = tasks.size() + 1;
        tasks.add(new Task(newId, name, description, priority, deadline, status, assignedUser));
    }

    public Task getTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public void deleteTaskById(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }
}