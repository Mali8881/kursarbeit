package org.example.kursarbeit;

import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // Пути к FXML-файлам
    private static final String MAIN_FXML = "/views/main_schedule.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Запуск с окна входа или регистрации
        openStartWindow(primaryStage);
    }

    // Начальное окно с кнопками "Войти" и "Зарегистрироваться"
    private void openStartWindow(Stage primaryStage) {
        primaryStage.setTitle("Персональный менеджер");

        VBox startLayout = new VBox(15);
        startLayout.setPadding(new Insets(20));

        Button loginButton = new Button("Войти");
        Button registerButton = new Button("Зарегистрироваться");

        startLayout.getChildren().addAll(loginButton, registerButton);

        loginButton.setOnAction(e -> openLoginForm(primaryStage));
        registerButton.setOnAction(e -> openRegisterForm(primaryStage));

        primaryStage.setScene(new Scene(startLayout, 300, 250));
        primaryStage.show();
    }

    // Форма входа
    private void openLoginForm(Stage primaryStage) {
        VBox loginLayout = new VBox(15);
        loginLayout.setPadding(new Insets(10));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Введите имя пользователя");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Введите пароль");

        Button loginButton = new Button("Войти");

        loginLayout.getChildren().addAll(
                new Label("Авторизация"),
                new Label("Имя пользователя:"), usernameField,
                new Label("Пароль:"), passwordField,
                loginButton
        );

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Проверка авторизации
            if (Database.authenticateUser(username, password)) {
                showAlert(Alert.AlertType.INFORMATION, "Успех", "Вход выполнен успешно!");
                openMainWindow(primaryStage); // Переход на главное окно
            } else {
                showAlert(Alert.AlertType.ERROR, "Ошибка", "Неверное имя пользователя или пароль.");
            }
        });

        primaryStage.setScene(new Scene(loginLayout, 300, 250));
    }

    // Форма регистрации
    private void openRegisterForm(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/registerUser.fxml"));
            Parent root = loader.load();

            Stage registerStage = new Stage();
            registerStage.setTitle("Регистрация");
            registerStage.setScene(new Scene(root));
            registerStage.showAndWait();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось загрузить форму регистрации.");
            e.printStackTrace();
        }
    }



    // Главное окно
    private void openMainWindow(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(MAIN_FXML));
            primaryStage.setTitle("Главное окно");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось загрузить главное окно.");
            e.printStackTrace();
        }
    }
    private void openCalendarPage(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/calendar_page.fxml"));
            primaryStage.setTitle("Календарь");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось загрузить страницу календаря.");
            e.printStackTrace();
        }
    }

    // Метод для показа уведомлений
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void openContactsView() {
    }
}
