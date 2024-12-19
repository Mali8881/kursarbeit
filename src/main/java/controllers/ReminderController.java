package controllers;

import Manager_models.Remainder;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReminderController {

    @FXML private TableView<Remainder> remindersTable;
    @FXML private TableColumn<Remainder, Integer> idColumn;
    @FXML private TableColumn<Remainder, String> titleColumn;
    @FXML private TableColumn<Remainder, String> dateColumn;
    @FXML private TableColumn<Remainder, String> timeColumn;

    @FXML private TextField titleField;
    @FXML private DatePicker datePicker;
    @FXML private TextField timeField;

    private final ObservableList<Remainder> remindersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Настройка колонок таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(data -> {
            LocalDate date = data.getValue().getReminderDate().toLocalDate();
            return new SimpleStringProperty(date.toString());
        });
        timeColumn.setCellValueFactory(data -> {
            LocalTime time = data.getValue().getReminderDate().toLocalTime();
            return new SimpleStringProperty(time.toString());
        });

        // Установка данных в таблицу
        remindersTable.setItems(remindersList);
    }

    @FXML
    private void addReminder(ActionEvent actionEvent) {
        String title = titleField.getText();
        LocalDate date = datePicker.getValue();
        String time = timeField.getText();

        if (title.isEmpty() || date == null || time.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            LocalTime parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime reminderDate = LocalDateTime.of(date, parsedTime);
            Remainder newReminder = new Remainder(remindersList.size() + 1, title, "", reminderDate, false);
            remindersList.add(newReminder);
            clearFields();
            showAlert("Успех", "Напоминание успешно добавлено!");
        } catch (Exception e) {
            showAlert("Ошибка", "Введите корректное время в формате ЧЧ:ММ.");
        }
    }

    @FXML
    private void updateReminder(ActionEvent actionEvent) {
        Remainder selectedReminder = remindersTable.getSelectionModel().getSelectedItem();
        if (selectedReminder == null) {
            showAlert("Ошибка", "Выберите напоминание для обновления.");
            return;
        }

        String title = titleField.getText();
        LocalDate date = datePicker.getValue();
        String time = timeField.getText();

        if (title.isEmpty() || date == null || time.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            LocalTime parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime reminderDate = LocalDateTime.of(date, parsedTime);

            selectedReminder.titleProperty().set(title);
            selectedReminder.reminderDateProperty().set(reminderDate);
            remindersTable.refresh();
            clearFields();
            showAlert("Успех", "Напоминание успешно обновлено!");
        } catch (Exception e) {
            showAlert("Ошибка", "Введите корректное время в формате ЧЧ:ММ.");
        }
    }

    @FXML
    private void deleteReminder(ActionEvent actionEvent) {
        Remainder selectedReminder = remindersTable.getSelectionModel().getSelectedItem();
        if (selectedReminder != null) {
            remindersList.remove(selectedReminder);
            showAlert("Успех", "Напоминание успешно удалено!");
        } else {
            showAlert("Ошибка", "Выберите напоминание для удаления.");
        }
    }

    private void clearFields() {
        titleField.clear();
        datePicker.setValue(null);
        timeField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
