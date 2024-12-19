package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScheduleController {

    @FXML private Button burgerButton; // Бургер-кнопка
    @FXML private VBox sideMenu; // Боковое меню
    @FXML private StackPane contentPane; // Центральная область

    @FXML
    public void initialize() {
        // Обработчик для бургер-кнопки
        burgerButton.setOnAction(e -> toggleSideMenu());
    }

    private void toggleSideMenu() {
        boolean isVisible = sideMenu.isVisible();
        sideMenu.setVisible(!isVisible);
        sideMenu.setManaged(!isVisible);
    }

    // Методы для открытия страниц
    @FXML
    private void openTasksPage() {
        System.out.println("Открытие страницы задач");
        loadPage("/views/tasks.fxml");
    }

    @FXML
    private void openContactsPage() {
        System.out.println("Открытие страницы контактов");
        loadPage("/views/contacts.fxml");
    }

    @FXML
    private void openNotesPage() {
        System.out.println("Открытие страницы заметок");
        loadPage("/views/notes.fxml");
    }

    @FXML
    private void openProfilePage() {
        System.out.println("Открытие страницы профиля");
        loadPage("/views/profile.fxml");
    }

    @FXML
    private void openRemindersPage() {
        System.out.println("Открытие страницы напоминаний");
        loadPage("/views/reminders.fxml");
    }

    // Универсальный метод для загрузки страниц
    private void loadPage(String fxmlPath) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(
                    javafx.fxml.FXMLLoader.load(getClass().getResource(fxmlPath))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCalendarPage(ActionEvent actionEvent) {
        System.out.println("Откртие календаря ");
        loadPage("/views/calendar_page.fxml");
    }
}
