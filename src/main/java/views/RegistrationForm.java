package views;

import org.example.kursarbeit.controllers.AuthController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationForm extends Application {
    private final AuthController authController = new AuthController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Регистрация");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label usernameLabel = new Label("Имя пользователя:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();
        Button registerButton = new Button("Зарегистрироваться");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(registerButton, 1, 2);

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (authController.register(username, password)) {
                showAlert("Успех", "Регистрация успешна!");
                primaryStage.close();
            } else {
                showAlert("Ошибка", "Не удалось зарегистрировать пользователя.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
