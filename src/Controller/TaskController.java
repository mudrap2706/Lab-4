package Controller;

import Model.Task;
import Model.TaskManager;
import java.util.List;

public class TaskController {
    private TaskManager taskManager;

    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public List<Task> getAllTasks() {
        return taskManager.getTasks();
    }

    public List<Task> getTasksForUser(String username) {
        return taskManager.getTasksForUser(username);
    }

    public boolean registerUser(String username, String password, String role) {
        return taskManager.addUser(username, password, role);
    }

    public String authenticateUser(String username, String password) {
        return taskManager.authenticate(username, password);
    }

    public void addTask(String name, String description, String priority, java.util.Date deadline, String status, String assignedUser) {
        taskManager.addTask(name, description, priority, deadline, status, assignedUser);
    }

    public void editTask(int taskId, String name, String description, String priority, java.util.Date deadline, String status, String assignedUser) {
        Task task = taskManager.getTaskById(taskId);
        if (task != null) {
            task.setName(name);
            task.setDescription(description);
            task.setPriority(priority);
            task.setDeadline(deadline);
            task.setStatus(status);
            task.setAssignedUser(assignedUser);
        }
    }

    public void deleteTask(int taskId) {
        taskManager.deleteTaskById(taskId);
    }
}