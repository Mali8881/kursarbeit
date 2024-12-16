package org.example.kursarbeit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private ImageView profileImageView;

    @FXML
    private CheckBox themeCheckBox;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private CheckBox notificationsCheckBox;

    // Метод для сохранения профиля
    @FXML
    private void saveProfile() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String theme = themeCheckBox.isSelected() ? "Темная тема" : "Светлая тема";
        String language = languageComboBox.getValue();
        boolean notifications = notificationsCheckBox.isSelected();

        // Логика сохранения данных (например, в базу данных или файл)
        System.out.println("Имя: " + name);
        System.out.println("Email: " + email);
        System.out.println("Пароль: " + password);
        System.out.println("Тема: " + theme);
        System.out.println("Язык: " + language);
        System.out.println("Уведомления: " + notifications);

        // Можно добавить сохранение изображения профиля в базу данных, если нужно
    }

    // Метод для загрузки изображения
    @FXML
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Загружаем изображение в ImageView
            Image image = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);
        }
    }
}
