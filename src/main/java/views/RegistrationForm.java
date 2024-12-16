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

        // Поля ввода
        Label usernameLabel = new Label("Имя пользователя:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();
        Label emailLabel = new Label("Электронная почта:");  // Добавляем поле для email
        TextField emailField = new TextField();
        Button registerButton = new Button("Зарегистрироваться");

        // Добавляем элементы в сетку
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(emailLabel, 0, 2);  // Добавляем метку email
        grid.add(emailField, 1, 2);  // Добавляем поле для ввода email
        grid.add(registerButton, 1, 3);

        // Обработка нажатия кнопки
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();  // Получаем значение email

            if (authController.register(username, password, email)) {  // Передаем email в метод
                showAlert("Успех", "Регистрация успешна!");
                primaryStage.close();
            } else {
                showAlert("Ошибка", "Не удалось зарегистрировать пользователя.");
            }
        });

        Scene scene = new Scene(grid, 300, 250);  // Увеличиваем высоту окна для учета нового поля
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для отображения сообщений
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
