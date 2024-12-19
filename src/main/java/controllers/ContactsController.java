package controllers;

import Manager_models.User;
import database.FriendRequestDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.List;

public class ContactsController {
    @FXML private TableView<User> contactsTable;
    @FXML private TableColumn<User, String> nameColumn;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, String> phoneColumn;
    @FXML private TextField searchField;

    private List<User> contacts;

    @FXML
    public void initialize() {
        // Настройка колонок таблицы
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        emailColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        phoneColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhone()));

        loadContacts();
    }

    private void loadContacts() {
        // Логика загрузки контактов из базы данных (замените на реальную)
        System.out.println("Загрузка контактов...");
    }

    @FXML
    private void sendFriendRequest() {
        User selectedUser = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            boolean success = FriendRequestDAO.sendFriendRequest(1, selectedUser.getEmail());
            if (success) {
                showAlert("Успех", "Запрос отправлен пользователю: " + selectedUser.getEmail());
            } else {
                showAlert("Ошибка", "Не удалось отправить запрос.");
            }
        } else {
            showAlert("Ошибка", "Выберите пользователя из таблицы.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void shareTask(ActionEvent actionEvent) {
    }
}