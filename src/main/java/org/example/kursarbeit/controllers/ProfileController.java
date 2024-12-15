package org.example.kursarbeit.controllers;



import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    // Метод для сохранения профиля
    @FXML
    private void saveProfile() {
        String name = nameField.getText();
        String email = emailField.getText();

        // Сохранение данных (можно добавить логику для сохранения в базу данных или локальный файл)
        System.out.println("Имя: " + name);
        System.out.println("Email: " + email);
    }
}

