package controllers;

import Manager_models.ProfilePage;
import database.ProfileDAO;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;

public class ProfileController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField; // Добавлено поле emailField
    @FXML private PasswordField passwordField;
    @FXML private Label photoPathLabel;

    private ProfilePage profile;
    private int userId = 1; // Пример текущего идентификатора пользователя (замените на реальный ID)

    @FXML
    private void initialize() {
        profile = ProfileDAO.getProfileById(userId); // userId – текущий идентификатор пользователя
        if (profile != null) {
            usernameField.setText(profile.getUsername());
            emailField.setText(profile.getEmail());
            passwordField.setText(profile.getPassword());
            if (profile.getPhotoPath() != null && !profile.getPhotoPath().isEmpty()) {
                photoPathLabel.setText(profile.getPhotoPath());
            }
        } else {
            profile = new ProfilePage(userId, "", "", "", "");
        }
    }

    @FXML
    public void saveProfile() {
        profile.setUsername(usernameField.getText());
        profile.setEmail(emailField.getText());
        profile.setPassword(passwordField.getText());
        ProfileDAO.updateProfile(profile);
        showAlert("Успех", "Профиль успешно обновлен!");
    }

    @FXML
    public void uploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите фото");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            profile.setPhotoPath(selectedFile.getAbsolutePath());
            photoPathLabel.setText(selectedFile.getName());
        }
    }
    @FXML
    public void cancelChanges() {
        // Очистка полей ввода или возврат к первоначальным значениям
        if (profile != null) {
            usernameField.setText(profile.getUsername());
            emailField.setText(profile.getEmail());
            passwordField.setText(profile.getPassword());
            if (profile.getPhotoPath() != null && !profile.getPhotoPath().isEmpty()) {
                photoPathLabel.setText(profile.getPhotoPath());
            } else {
                photoPathLabel.setText("Нет изображения");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Указан тип Alert
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
