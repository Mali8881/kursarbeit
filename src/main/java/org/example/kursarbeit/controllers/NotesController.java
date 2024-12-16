package org.example.kursarbeit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class NotesController {

    @FXML
    private ListView<String> notesListView; // Список заметок

    @FXML
    private TextArea noteTextArea; // Текстовое поле для заметки

    // Метод для добавления заметки
    @FXML
    private void handleAddNote() {
        String noteContent = noteTextArea.getText().trim();
        if (!noteContent.isEmpty()) {
            // Добавить заметку в базу данных
            notesListView.getItems().add(noteContent);
            noteTextArea.clear();
        } else {
            showAlert("Ошибка", "Текст заметки не может быть пустым.");
        }
    }

    // Метод для редактирования заметки
    @FXML
    private void handleEditNote() {
        String noteContent = noteTextArea.getText().trim();
        String selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null && !noteContent.isEmpty()) {
            // Отредактировать заметку в базе данных
            int index = notesListView.getSelectionModel().getSelectedIndex();
            notesListView.getItems().set(index, noteContent);
            noteTextArea.clear();
        } else {
            showAlert("Ошибка", "Выберите заметку и введите новый текст.");
        }
    }

    // Метод для удаления заметки
    @FXML
    private void handleDeleteNote() {
        String selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            // Удалить заметку из базы данных
            notesListView.getItems().remove(selectedNote);
            noteTextArea.clear();
        } else {
            showAlert("Ошибка", "Выберите заметку для удаления.");
        }
    }

    // Метод для отображения сообщений
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Обработчик для выбора заметки
    @FXML
    private void handleNoteSelection() {
        String selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            noteTextArea.setText(selectedNote);
        }
    }
}
