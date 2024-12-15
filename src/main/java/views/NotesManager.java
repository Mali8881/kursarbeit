package views;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class NotesManager extends Application {

    // Пример базы данных заметок
    private ListView<String> notesListView = new ListView<>();
    private TextArea noteTextArea = new TextArea();
    private Button addButton = new Button("Добавить");
    private Button editButton = new Button("Редактировать");
    private Button deleteButton = new Button("Удалить");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Создание основных элементов интерфейса
        notesListView.setPrefHeight(200);
        noteTextArea.setPromptText("Введите текст заметки...");
        noteTextArea.setPrefHeight(100);

        // Панель для кнопок
        HBox buttonsPanel = new HBox(10, addButton, editButton, deleteButton);
        buttonsPanel.setStyle("-fx-alignment: center;");

        // Лейаут
        VBox layout = new VBox(10, notesListView, noteTextArea, buttonsPanel);
        layout.setStyle("-fx-padding: 10;");

        // Обработчики событий для кнопок
        addButton.setOnAction(e -> addNote());
        editButton.setOnAction(e -> editNote());
        deleteButton.setOnAction(e -> deleteNote());

        // Обработчик для выделения заметки из списка
        notesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                noteTextArea.setText(newValue);
            }
        });

        // Настройка сцены
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Заметки");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для добавления заметки
    private void addNote() {
        String noteContent = noteTextArea.getText().trim();
        if (!noteContent.isEmpty()) {
            // Добавить заметку в базу данных
            // Например, тут будет добавление в базу, а не просто в список
            notesListView.getItems().add(noteContent);
            noteTextArea.clear();
        } else {
            showAlert("Ошибка", "Текст заметки не может быть пустым.");
        }
    }

    // Метод для редактирования заметки
    private void editNote() {
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
    private void deleteNote() {
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
}
