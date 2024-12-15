package org.example.kursarbeit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotesController {

    @FXML
    private TextArea noteTextArea;

    @FXML
    private void handleSaveNote() {
        String noteContent = noteTextArea.getText().trim();

        if (!noteContent.isEmpty()) {
            // Сохранение заметки в базе данных
            saveNoteToDatabase(noteContent);

            // Очистка поля ввода
            noteTextArea.clear();

            // Показать сообщение о сохранении
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText("Заметка сохранена");
            alert.showAndWait();
        } else {
            // Сообщение об ошибке
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пустая заметка");
            alert.setContentText("Пожалуйста, введите текст заметки.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBackToMain() {
        // Переход обратно на главную страницу
        // В классе Main добавьте обработку для перехода на главную страницу
    }

    private void saveNoteToDatabase(String noteContent) {
        // Логика для сохранения заметки в базе данных
        // Например, с использованием класса Database и его метода для добавления заметок
        // Database.addNoteToDatabase(noteContent);
        System.out.println("Заметка сохранена: " + noteContent);  // Временно для тестирования
    }
}
