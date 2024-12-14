package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class ProfilePage extends Application {
    private String userName = "Иван Иванов";
    private String userEmail = "ivan@example.com";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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
        primaryStage.setTitle("Профиль");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Данные сохранены");
        alert.setContentText("Ваши изменения были успешно сохранены.");
        alert.showAndWait();
    }

    public void openProfilesSection(Stage profileStage) {
        start(profileStage);
    }
}
