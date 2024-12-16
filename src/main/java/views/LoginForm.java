package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import database.Database;

public class LoginForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Авторизация");

        // Основной контейнер (GridPane)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Поля формы
        Label usernameLabel = new Label("Имя пользователя:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Войти");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Проверка на пустые поля
            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Ошибка", "Пожалуйста, заполните все поля.");
                return;
            }

            // Проверяем данные в базе
            if (authenticateUser(username, password)) {
                showAlert("Успех", "Авторизация успешна!");
                primaryStage.close();
                openMainApp(); // Переход в главное приложение
            } else {
                showAlert("Ошибка", "Неверное имя пользователя или пароль.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для проверки данных в базе
    private boolean authenticateUser(String username, String password) {
        return Database.authenticateUser(username, password);
    }

    // Отображение сообщения
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Открытие главного окна приложения
    private void openMainApp() {
        Stage mainStage = new Stage();
        mainStage.setTitle("Главное приложение");
        Label welcomeLabel = new Label("Добро пожаловать в приложение!");
        Scene mainScene = new Scene(welcomeLabel, 400, 200);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
