package org.example.kursarbeit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import views.Task;

import java.time.LocalDate;

public class TasksController {

    @FXML
    private TextField filterField;
    @FXML
    private ComboBox<String> filterPriority;
    @FXML
    private ComboBox<String> filterCategory;
    @FXML
    private DatePicker filterDate;

    @FXML
    private TextField taskTitleField;
    @FXML
    private TextField taskDescriptionField;
    @FXML
    private ComboBox<String> taskCategoryField;
    @FXML
    private DatePicker taskStartDateField;
    @FXML
    private DatePicker taskDueDateField;
    @FXML
    private ComboBox<String> taskPriorityField;

    @FXML
    private Button addTaskButton;
    @FXML
    private Button deleteTaskButton;
    @FXML
    private Button updateTaskButton;

    @FXML
    private TableView<Task> tasksTable;
    @FXML
    private TableColumn<Task, String> titleColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> categoryColumn;
    @FXML
    private TableColumn<Task, LocalDate> startDateColumn;
    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;
    @FXML
    private TableColumn<Task, String> priorityColumn;
    @FXML
    private TableColumn<Task, String> statusColumn;

    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    public void initialize() {
        // Инициализация таблицы
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
        priorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tasksTable.setItems(taskList);

        // Добавление значений в комбобоксы
        filterPriority.getItems().addAll("Низкий", "Средний", "Высокий");
        filterCategory.getItems().addAll("Работа", "Личное", "Учеба");
        taskPriorityField.getItems().addAll("Низкий", "Средний", "Высокий");
        taskCategoryField.getItems().addAll("Работа", "Личное", "Учеба");
    }
    // В классе TasksController
    private static int idCounter = 0;

    @FXML
    private void addTask() {
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        String category = taskCategoryField.getValue();
        LocalDate startDate = taskStartDateField.getValue();
        LocalDate dueDate = taskDueDateField.getValue();
        String priority = taskPriorityField.getValue();


        Task newTask = new Task(String.valueOf(idCounter++), title, description, category, startDate, dueDate, priority, "Не выполнено");

        taskList.add(newTask);

        clearFields();
    }

    @FXML
    private void deleteTask() {
        Task selectedTask = tasksTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskList.remove(selectedTask);
        }
    }

    @FXML
    private void updateTask() {
        Task selectedTask = tasksTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            selectedTask.setTitle(taskTitleField.getText());
            selectedTask.setDescription(taskDescriptionField.getText());
            selectedTask.setCategory(taskCategoryField.getValue());
            selectedTask.setStartDate(taskStartDateField.getValue());
            selectedTask.setDueDate(taskDueDateField.getValue());
            selectedTask.setPriority(taskPriorityField.getValue());
            tasksTable.refresh();
        }
    }

    private void clearFields() {
        taskTitleField.clear();
        taskDescriptionField.clear();
        taskCategoryField.setValue(null);
        taskStartDateField.setValue(null);
        taskDueDateField.setValue(null);
        taskPriorityField.setValue(null);
    }
}
