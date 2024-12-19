package controllers;

import database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;





public class AuthController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (Database.authenticateUser(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Вход выполнен!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Неверные данные.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

