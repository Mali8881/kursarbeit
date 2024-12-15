package org.example.kursarbeit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import org.example.kursarbeit.controllers.TasksController;

public class Main extends Application {

    private TasksController tasksController;

    public static void main(String[] args) {
        launch(args);
    }

    private VBox createMainLayout(Stage primaryStage) {
        // Создание текста для статистики
        Text statisticsText = createStatisticsText();

        // Создание кнопок для навигации
        Button tasksButton = createNavigationButton("Задачи", "#4CAF50", e -> openTasksSection(primaryStage));
        Button contactsButton = createNavigationButton("Контакты", "#2196F3", e -> openContactsSection());
        Button notesButton = createNavigationButton("Заметки", "#FF5722", e -> openNotesSection());
        Button remindersButton = createNavigationButton("Напоминания", "#FFC107", e -> openRemindersSection(primaryStage)); // кнопка для напоминаний
        Button profileButton = createNavigationButton("Профиль", "#795548", e -> openProfilePage());

        // Расположение элементов в интерфейсе
        VBox layout = new VBox(15, statisticsText, tasksButton, contactsButton, notesButton, remindersButton, profileButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        return layout;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загрузка главной страницы
        VBox mainLayout = createMainLayout(primaryStage);
        Scene mainScene = new Scene(mainLayout, 500, 500);
        primaryStage.setTitle("Персональный менеджер");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private Text createStatisticsText() {
        Text statisticsText = new Text("Статистика:");
        statisticsText.setFont(Font.font("Arial", 16));
        statisticsText.setFill(Color.DARKBLUE);
        statisticsText.setText(statisticsText.getText() +
                "\nЗадач: " + 0 +  // Это временно
                "\nЗаметок: " + 0 +
                "\nНапоминаний: " + 0);
        return statisticsText;
    }

    private Button createNavigationButton(String text, String color, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        button.setPrefWidth(200);
        button.setOnAction(action);
        return button;
    }

    // Открытие раздела задач
    private void openTasksSection(Stage primaryStage) {
        System.out.println("Открыт раздел задач");
        try {
            // Загрузка FXML файла для задач
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tasks.fxml"));
            Parent root = loader.load();
            tasksController = loader.getController();

            // Открытие новой сцены с задачами
            Scene tasksScene = new Scene(root);
            primaryStage.setScene(tasksScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openNotesSection() {
        System.out.println("Открыть раздел заметок");
        try {
            // Загрузка FXML файла для заметок
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notes.fxml"));
            Parent root = loader.load();

            // Открытие новой сцены с заметками
            Stage stage = new Stage();
            Scene notesScene = new Scene(root);
            stage.setScene(notesScene);
            stage.setTitle("Заметки");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openRemindersSection(Stage primaryStage) {
        System.out.println("Открыть раздел напоминаний");
        try {
            // Загрузка FXML файла для напоминаний
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reminders.fxml"));
            Parent root = loader.load();

            // Открытие новой сцены с напоминаниями
            Stage stage = new Stage();
            Scene remindersScene = new Scene(root);
            stage.setScene(remindersScene);
            stage.setTitle("Напоминания");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Заглушки для других разделов
    private void openContactsSection() {
        System.out.println("Открыть раздел контактов");
    }

    private void openProfilePage() {
        System.out.println("Открыть профиль");
        try {
            // Загрузка FXML файла для профиля
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();

            // Создание нового окна
            Stage profileStage = new Stage();
            Scene profileScene = new Scene(root);
            profileStage.setScene(profileScene);
            profileStage.setTitle("Профиль");
            profileStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
