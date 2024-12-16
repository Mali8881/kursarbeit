package org.example.kursarbeit;

import javafx.fxml.FXMLLoader; // Для работы с FXML
import javafx.scene.Parent;    // Для родительского компонента в FXML
import java.io.IOException;    // Для обработки исключений ввода/вывода
import org.example.kursarbeit.controllers.ProfileController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;  // Используем javafx.scene.text.Text для работы с текстом в JavaFX
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import database.Database;
import org.example.kursarbeit.controllers.RemindersController;
import org.example.kursarbeit.controllers.TasksController;

public class Main extends Application {

    private TasksController tasksController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Персональный менеджер");

        // Главное окно с выбором действия: Войти или Зарегистрироваться
        VBox startLayout = new VBox(15);
        startLayout.setPadding(new Insets(20));

        Button loginButton = new Button("Войти");
        Button registerButton = new Button("Зарегистрироваться");

        startLayout.getChildren().addAll(loginButton, registerButton);

        loginButton.setOnAction(e -> openLoginForm(primaryStage));
        registerButton.setOnAction(e -> openRegisterForm(primaryStage));

        Scene startScene = new Scene(startLayout, 300, 250);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    // Открытие формы входа
    private void openLoginForm(Stage primaryStage) {
        VBox loginLayout = new VBox(15);
        loginLayout.setPadding(new Insets(10));
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Войти");

        loginLayout.getChildren().addAll(
                new Label("Имя пользователя:"), usernameField,
                new Label("Пароль:"), passwordField,
                loginButton
        );

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Проверка авторизации
            if (Database.authenticateUser(username, password)) {
                primaryStage.close();  // Закрытие окна авторизации
                openMainApp(primaryStage);  // Открытие главного окна
            } else {
                showAlert("Ошибка", "Неверное имя пользователя или пароль.");
            }
        });

        Scene loginScene = new Scene(loginLayout, 300, 250);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Открытие формы регистрации
    private void openRegisterForm(Stage primaryStage) {
        VBox registerLayout = new VBox(15);
        registerLayout.setPadding(new Insets(10));
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField emailField = new TextField();
        Button registerButton = new Button("Зарегистрироваться");

        registerLayout.getChildren().addAll(
                new Label("Имя пользователя:"), usernameField,
                new Label("Пароль:"), passwordField,
                new Label("Email:"), emailField,
                registerButton
        );

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();

            // Проверка на пустые поля
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                showAlert("Ошибка", "Пожалуйста, заполните все поля.");
                return;
            }

            // Регистрация пользователя
            boolean success = Database.registerUser(username, password, email);
            if (success) {
                showAlert("Успех", "Регистрация прошла успешно!");
                primaryStage.close();  // Закрытие окна регистрации
                openMainApp(primaryStage);  // Открытие главного окна
            } else {
                showAlert("Ошибка", "Ошибка при регистрации.");
            }
        });

        Scene registerScene = new Scene(registerLayout, 300, 250);
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    // Открытие главного окна приложения
    private void openMainApp(Stage primaryStage) {
        VBox mainLayout = createMainLayout(primaryStage);
        Scene mainScene = new Scene(mainLayout, 500, 500);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Персональный менеджер");
        primaryStage.show();
    }

    private VBox createMainLayout(Stage primaryStage) {
        // Создание текста для статистики
        Text statisticsText = createStatisticsText();

        // Создание кнопок для навигации
        Button tasksButton = createNavigationButton("Задачи", "#4CAF50", e -> openTasksSection(primaryStage));
        Button contactsButton = createNavigationButton("Контакты", "#2196F3", e -> openContactsSection());
        Button notesButton = createNavigationButton("Заметки", "#FF5722", e -> openNotesSection());
        Button remindersButton = createNavigationButton("Напоминания", "#FFC107", e -> openRemindersSection(primaryStage));
        Button profileButton = createNavigationButton("Профиль", "#795548", e -> openProfilePage());

        // Расположение элементов в интерфейсе
        VBox layout = new VBox(15, statisticsText, tasksButton, contactsButton, notesButton, remindersButton, profileButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        return layout;
    }

    private Text createStatisticsText() {
        Text statisticsText = new Text("Статистика:\nЗадач: 0\nЗаметок: 0\nНапоминаний: 0");
        statisticsText.setFont(Font.font("Arial", 16));  // Установка шрифта
        statisticsText.setFill(Color.DARKBLUE);  // Установка цвета
        return statisticsText;
    }

    private Button createNavigationButton(String text, String color, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        button.setPrefWidth(200);
        button.setOnAction(action);
        return button;
    }

    public void openTasksSection(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tasks.fxml"));

            Parent root = loader.load();
            TasksController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage tasksStage = new Stage();
            tasksStage.setScene(scene);
            tasksStage.setTitle("Задачи");
            tasksStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void openNotesSection() {
        System.out.println("Открыть раздел заметок");
        // Логика для открытия заметок
    }

    public void openRemindersSection(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reminders.fxml"));

            Parent root = loader.load();
            RemindersController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage remindersStage = new Stage();
            remindersStage.setScene(scene);
            remindersStage.setTitle("Напоминания");
            remindersStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void openContactsSection() {
        System.out.println("Открыть раздел контактов");
        // Логика для открытия контактов
    }

    private void openProfilePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage profileStage = new Stage();
            profileStage.setScene(new Scene(root));
            profileStage.setTitle("Профиль");
            profileStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

