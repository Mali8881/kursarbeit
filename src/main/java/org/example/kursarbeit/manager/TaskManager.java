package org.example.kursarbeit.manager;

import views.Task;  // Добавлен импорт класса Task
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class TaskManager {
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private int idCounter = 1;  // Счетчик для id задач

    public TaskManager() {
        // Пример задач с всеми необходимыми полями
        tasks.add(new Task(String.valueOf(idCounter++), "Задача 1", "Описание задачи 1", "Работа", LocalDate.now(), LocalDate.of(2024, 12, 20), "Высокий", "Новая"));
        tasks.add(new Task(String.valueOf(idCounter++), "Задача 2", "Описание задачи 2", "Личное", LocalDate.now(), LocalDate.of(2024, 12, 21), "Средний", "В процессе"));
        tasks.add(new Task(String.valueOf(idCounter++), "Задача 3", "Описание задачи 3", "Работа", LocalDate.now(), LocalDate.of(2024, 12, 22), "Низкий", "Завершена"));
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}
