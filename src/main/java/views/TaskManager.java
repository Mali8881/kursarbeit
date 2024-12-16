package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class TaskManager {
    private ObservableList<Task> tasks;

    public TaskManager() {
        tasks = FXCollections.observableArrayList();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void updateTask(Task task, String title, String description, LocalDate dueDate, String priority) {
        task.titleProperty().set(title);
        task.descriptionProperty().set(description);
        task.dueDateProperty().set(dueDate);
        task.priorityProperty().set(priority);
    }
}
