package controllers;

import Manager_models.TaskManager;
import database.TaskManagerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class TaskManagerController {

    // TableView и столбцы
    @FXML private TableView<TaskManager> taskTableView;
    @FXML private TableColumn<TaskManager, Integer> idColumn;
    @FXML private TableColumn<TaskManager, String> titleColumn;
    @FXML private TableColumn<TaskManager, String> descriptionColumn;
    @FXML private TableColumn<TaskManager, String> categoryColumn;
    @FXML private TableColumn<TaskManager, LocalDate> startDateColumn;
    @FXML private TableColumn<TaskManager, LocalDate> dueDateColumn;
    @FXML private TableColumn<TaskManager, String> priorityColumn;
    @FXML private TableColumn<TaskManager, String> statusColumn;

    // Поля ввода
    @FXML private TextField titleField;
    @FXML private TextArea descriptionField;
    @FXML private TextField categoryField;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker dueDateField;
    @FXML private ChoiceBox<String> priorityField;
    @FXML private ChoiceBox<String> statusField;
    @FXML
    private ChoiceBox<String> filterStatusField;




    // Поля поиска и фильтрации
    @FXML private TextField searchField;

    @FXML private ChoiceBox<String> filterPriorityField;

    private final ObservableList<TaskManager> taskList = FXCollections.observableArrayList();
    private TaskManager selectedTask;



    @FXML
    public void initialize() {
        // Проверка на null
        if (filterStatusField == null) {
            System.out.println("filterStatusField не был привязан из FXML!");
        } else {
            filterStatusField.setItems(FXCollections.observableArrayList("Все", "Не начато", "В процессе", "Завершено"));
        }

        // Настройка других компонентов
        setupFormControls();
        setupSearchAndFilters();
        configureTableView();
        loadTasks();
    }


    @FXML
    public void clearFilters() {
        searchField.clear();
        filterStatusField.setValue("Все");
        filterPriorityField.setValue("Все");
        loadTasks(); // Заново загружаем все задачи
        showAlert(Alert.AlertType.INFORMATION, "Фильтры очищены", "Фильтры были успешно сброшены.");
    }



    // Настройка TableView
    private void configureTableView() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        dueDateColumn.setCellValueFactory(cellData -> cellData.getValue().dueDateProperty());
        priorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        taskTableView.setItems(taskList);

        taskTableView.setOnMouseClicked(this::populateFieldsFromSelection);
    }

    // Загрузка задач из базы данных
    private void loadTasks() {
        taskList.clear();
        taskList.addAll(TaskManagerDAO.getAllTasks());
    }

    // Настройка элементов управления формой
    private void setupFormControls() {
        priorityField.setItems(FXCollections.observableArrayList("Низкий", "Средний", "Высокий"));
        statusField.setItems(FXCollections.observableArrayList("Не начато", "В процессе", "Завершено"));
    }

    // Настройка поиска и фильтров
    private void setupSearchAndFilters() {
        filterStatusField.setItems(FXCollections.observableArrayList("Все", "Не начато", "В процессе", "Завершено"));
        filterPriorityField.setItems(FXCollections.observableArrayList("Все", "Низкий", "Средний", "Высокий"));
        filterStatusField.setValue("Все");
        filterPriorityField.setValue("Все");

        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterTasks());
        filterStatusField.valueProperty().addListener((observable, oldValue, newValue) -> filterTasks());
        filterPriorityField.valueProperty().addListener((observable, oldValue, newValue) -> filterTasks());
    }

    // Добавление задачи
    @FXML
    public void addTask() {
        TaskManager task = new TaskManager(
                0, titleField.getText(), descriptionField.getText(), categoryField.getText(),
                startDateField.getValue(), dueDateField.getValue(),
                priorityField.getValue(), statusField.getValue()
        );

        if (TaskManagerDAO.addTask(task)) {
            loadTasks();
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Задача добавлена!");
        }
    }

    // Обновление задачи
    @FXML
    public void updateTask() {
        if (selectedTask != null) {
            selectedTask.setTitle(titleField.getText());
            selectedTask.setDescription(descriptionField.getText());
            selectedTask.setCategory(categoryField.getText());
            selectedTask.setStartDate(startDateField.getValue());
            selectedTask.setDueDate(dueDateField.getValue());
            selectedTask.setPriority(priorityField.getValue());
            selectedTask.setStatus(statusField.getValue());

            if (TaskManagerDAO.updateTask(selectedTask)) {
                loadTasks();
                clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Успех", "Задача обновлена!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Ошибка", "Выберите задачу для обновления.");
        }
    }

    // Удаление задачи
    @FXML
    public void deleteTask() {
        if (selectedTask != null) {
            if (TaskManagerDAO.deleteTask(selectedTask.getId())) {
                loadTasks();
                clearForm();
                showAlert(Alert.AlertType.INFORMATION, "Успех", "Задача удалена!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Ошибка", "Выберите задачу для удаления.");
        }
    }

    // Очистка формы
    @FXML
    public void clearForm() {
        titleField.clear();
        descriptionField.clear();
        categoryField.clear();
        startDateField.setValue(null);
        dueDateField.setValue(null);
        priorityField.setValue(null);
        statusField.setValue(null);
        selectedTask = null;
    }

    // Заполнение формы из TableView
    private void populateFieldsFromSelection(MouseEvent event) {
        selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            titleField.setText(selectedTask.getTitle());
            descriptionField.setText(selectedTask.getDescription());
            categoryField.setText(selectedTask.getCategory());
            startDateField.setValue(selectedTask.getStartDate());
            dueDateField.setValue(selectedTask.getDueDate());
            priorityField.setValue(selectedTask.getPriority());
            statusField.setValue(selectedTask.getStatus());
        }
    }

    // Фильтрация задач
    private void filterTasks() {
        String searchText = searchField.getText().toLowerCase();
        String selectedStatus = filterStatusField.getValue();
        String selectedPriority = filterPriorityField.getValue();

        taskList.setAll(TaskManagerDAO.getAllTasks().stream()
                .filter(task -> task.getTitle().toLowerCase().contains(searchText) ||
                        task.getCategory().toLowerCase().contains(searchText))
                .filter(task -> selectedStatus.equals("Все") || task.getStatus().equals(selectedStatus))
                .filter(task -> selectedPriority.equals("Все") || task.getPriority().equals(selectedPriority))
                .collect(Collectors.toList()));
    }

    // Отображение сообщений
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void displayAllTasks(ActionEvent actionEvent) {
    }
}

