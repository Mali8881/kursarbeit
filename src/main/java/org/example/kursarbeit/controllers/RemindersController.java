package org.example.kursarbeit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import views.ReminderManager;  // Исправляем импорт

import java.time.LocalDateTime;
import java.util.Optional;

public class RemindersController {

    @FXML
    private ListView<String> remindersListView;

    // Метод для добавления напоминания, вызываемый из FXML
    @FXML
    public void onAddReminderClicked() {
        addReminder();  // Вызываем метод для добавления напоминания
    }
    @FXML
    private void addReminder() {
        System.out.println("Добавить напоминание");

    // Приватный метод для создания и добавления напоминания

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Добавить Напоминание");
        dialog.setHeaderText("Введите текст напоминания");
        dialog.setContentText("Напоминание:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String content = result.get();
            LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);  // Напоминание через 10 минут (можно изменить)

            ReminderManager newReminder = new ReminderManager(content, dateTime);
            remindersListView.getItems().add(newReminder.toString());
        }
    }
}
