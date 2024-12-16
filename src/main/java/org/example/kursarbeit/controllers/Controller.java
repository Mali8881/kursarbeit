package org.example.kursarbeit.controllers;
import database.Database;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.sql.*;

public class Controller {

    @FXML
    private ImageView profileImageView;

    // Метод для загрузки изображения и сохранения пути в базу данных
    @FXML
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Загружаем изображение в ImageView
            Image image = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);

            // Получаем абсолютный путь к изображению
            String imagePath = selectedFile.getAbsolutePath();

            // Сохраняем путь в базу данных
            saveImagePath(imagePath);
        }
    }

    // Сохранение пути изображения в базу данных
    private void saveImagePath(String imagePath) {
        String sql = "UPDATE user_profile SET profile_image_path = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Замените id на идентификатор текущего пользователя
            stmt.setString(1, imagePath);
            stmt.setInt(2, 1);  // Например, id пользователя 1

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Путь изображения успешно сохранен в базе данных.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

