package org.example.kursarbeit.controllers;

import org.example.kursarbeit.manager.TaskManager;
import views.Task;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TasksController {

    @FXML
    private TextField taskTitleField;

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private TextField taskDueDateField;

    @FXML
    private TextField taskPriorityField;

    @FXML
    private Button addTaskButton;

    @FXML
    private ListView<Task> taskListView; // Для отображения задач в ListView

    @FXML
    private TextField filterField; // Поле для фильтрации

    @FXML
    private TableView<Task> tasksTable; // Таблица задач

    @FXML
    private TableColumn<Task, String> titleColumn; // Столбец для названия задачи
    @FXML
    private TableColumn<Task, String> descriptionColumn; // Столбец для описания задачи
    @FXML
    private TableColumn<Task, String> dueDateColumn; // Столбец для даты задачи
    @FXML
    private TableColumn<Task, String> priorityColumn; // Столбец для приоритета задачи

    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private TaskManager taskManager = new TaskManager();

    @FXML
    private void initialize() {
        assert taskListView != null : "fx:id=\"taskListView\" was not injected: check your FXML file 'tasks.fxml'.";
        // Настройка колонки в TableView
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty().asString());
        priorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());

        addTaskButton.setOnAction(event -> addTask());

        // Инициализация фильтра
        FilteredList<Task> filteredData = new FilteredList<>(tasks, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(task -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Без фильтра, показывать все задачи
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return task.getTitle().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Task> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tasksTable.comparatorProperty());

        // Устанавливаем отсортированные и отфильтрованные данные в таблицу
        tasksTable.setItems(sortedData);
    }

    public void addTask() {
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        String dueDateString = taskDueDateField.getText();
        String priority = taskPriorityField.getText();

        if (!title.isEmpty() && !description.isEmpty() && !dueDateString.isEmpty() && !priority.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            try {
                // Парсим строку с датой в объект LocalDate
                LocalDate dueDate = LocalDate.parse(dueDateString, formatter);

                // Создаем новую задачу
                Task task = new Task(title, description, dueDate, priority);

                // Добавляем задачу в ObservableList, если он существует
                tasks.add(task);

                // Добавляем задачу в ListView, если taskListView инициализирован
                if (taskListView != null) {
                    taskListView.getItems().add(task);
                } else {
                    System.out.println("taskListView не инициализирован.");
                }

                // Очищаем поля ввода
                taskTitleField.clear();
                taskDescriptionField.clear();
                taskDueDateField.clear();
                taskPriorityField.clear();
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты! Используйте dd.MM.yyyy.");
            }
        } else {
            System.out.println("Пожалуйста, заполните все поля.");
        }
    }
}