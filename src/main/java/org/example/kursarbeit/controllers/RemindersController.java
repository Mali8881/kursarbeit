package org.example.kursarbeit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import database.Database;
import views.ReminderManager;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class RemindersController {

    @FXML
    private ListView<String> remindersListView;

    // Метод для загрузки напоминаний из базы данных
    public void initialize() {
        ObservableList<ReminderManager> reminders = Database.getReminders();
        for (ReminderManager reminder : reminders) {
            remindersListView.getItems().add(reminder.toString());
        }
    }

    // Метод для добавления нового напоминания
    @FXML
    private void addReminder() {
        // Логика для добавления нового напоминания
        String content = "New Reminder";  // Пример данных
        LocalDateTime dateTime = LocalDateTime.now();  // Текущее время

        boolean success = Database.addReminder(content, dateTime);
        if (success) {
            remindersListView.getItems().add(content + " | Время: " + dateTime);
        } else {
            showAlert("Ошибка", "Не удалось добавить напоминание.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
