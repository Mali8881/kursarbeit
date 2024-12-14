package org.example.kursarbeit.controllers;



import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ProfileController {
    private String userName = "Иван Иванов";
    private String userEmail = "ivan@example.com";


    public void openProfilesSection(Stage stage) {
        // Теперь переменная stage доступна
        stage.setTitle("Профиль");
        // Здесь можете добавить код для настройки сцены
        // Заголовок страницы
        Text pageTitle = new Text("Профиль");
        pageTitle.setFont(Font.font("Arial", 20));

        // Поля для редактирования информации
        TextField nameField = new TextField(userName);
        nameField.setPromptText("Имя");

        TextField emailField = new TextField(userEmail);
        emailField.setPromptText("Email");

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            userName = nameField.getText();
            userEmail = emailField.getText();
            showConfirmation();
        });

        // Компоновка элементов
        VBox layout = new VBox(20, pageTitle, new Label("Имя:"), nameField, new Label("Email:"), emailField, saveButton);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Профиль");
        stage.setScene(scene);
        stage.show();
    }

    private void showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Данные сохранены");
        alert.setContentText("Ваши изменения были успешно сохранены.");
        alert.showAndWait();
    }
}

