package org.example.kursarbeit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.time.LocalDate;  // Импортируем LocalDate
import org.example.kursarbeit.controllers.TasksController;  // Импортируем TasksController
import views.NotesManager;
import views.ProfilePage;
import views.ReminderManager;
import views.TaskManager;

public class Main extends Application {

    private TaskManager taskManager;
    private NotesManager notesManager;
    private ReminderManager reminderManager;
    private ProfilePage profileManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeManagers();
        addDemoData();

        VBox layout = createMainLayout(primaryStage);

        Scene scene = new Scene(layout, 400, 400);
        scene.setFill(Color.LIGHTGRAY);

        primaryStage.setTitle("Главная страница");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeManagers() {
        taskManager = new TaskManager();
        notesManager = new NotesManager();
        reminderManager = new ReminderManager();
        profileManager = new ProfilePage();
    }

    private void addDemoData() {
        taskManager.addTask(new TaskManager.TaskItem(1, "Task 1", "Description", LocalDate.now(), "Pending"));
        taskManager.addTask(new TaskManager.TaskItem(2, "Task 2", "Description", LocalDate.now().plusDays(1), "Completed"));
        notesManager.addNote(new NotesManager.Note(1, "Note 1", "Important note"));
        reminderManager.addReminder(new ReminderManager.Reminder(1, "Reminder 1", LocalDate.now()));
    }

    private VBox createMainLayout(Stage primaryStage) {
        Text statisticsText = createStatisticsText();

        Button tasksButton = createNavigationButton("Задачи", "#4CAF50", e -> openTasksSection(primaryStage));
        Button contactsButton = createNavigationButton("Контакты", "#2196F3", e -> openContactsSection());
        Button notesButton = createNavigationButton("Заметки", "#FF5722", e -> openNotesSection(primaryStage));
        Button remindersButton = createNavigationButton("Напоминания", "#FFC107", e -> openRemindersSection(primaryStage));
        Button profileButton = createNavigationButton("Профиль", "#795548", e -> openProfilePage(primaryStage));

        Button backButton = createNavigationButton("На главную", "#607D8B", e -> start(primaryStage));

        VBox layout = new VBox(15, statisticsText, tasksButton, contactsButton, notesButton, remindersButton, profileButton, backButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    private Text createStatisticsText() {
        Text statisticsText = new Text("Статистика:");
        statisticsText.setFont(Font.font("Arial", 16));
        statisticsText.setFill(Color.DARKBLUE);
        statisticsText.setText(statisticsText.getText() +
                "\nЗадач: " + taskManager.getTasks().size() +
                "\nЗаметок: " + notesManager.getNotes().size() +
                "\nНапоминаний: " + reminderManager.getReminders().size());
        return statisticsText;
    }

    private Button createNavigationButton(String text, String color, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        button.setPrefWidth(200);
        button.setOnAction(action);
        return button;
    }

    private void openTasksSection(Stage parentStage) {
        TasksController taskViewController = new TasksController();
        taskViewController.openTasksSection(parentStage);
    }

    private void openContactsSection() {
        System.out.println("Открыть раздел контактов");
    }

    private void openNotesSection(Stage parentStage) {
        notesManager.openNotesSection(parentStage);
    }

    private void openProfilePage(Stage parentStage) {
        profileManager.openProfilesSection(parentStage);
    }

    private void openRemindersSection(Stage parentStage) {
        reminderManager.openRemindersSection(parentStage);
    }
}
