package org.example.kursarbeit.manager;

import views.Task;  // Add this import

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class TaskManager {
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public TaskManager() {
        // Example tasks with all required fields (title, description, due date, priority)
        tasks.add(new Task("Задача 1", "Описание задачи 1", LocalDate.of(2024, 12, 20), "Высокий"));
        tasks.add(new Task("Задача 2", "Описание задачи 2", LocalDate.of(2024, 12, 21), "Средний"));
        tasks.add(new Task("Задача 3", "Описание задачи 3", LocalDate.of(2024, 12, 22), "Низкий"));
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}
