package controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CalendarPageController {

    @FXML private DatePicker calendarDatePicker;
    @FXML private ListView<String> eventsListView;

    private final Map<LocalDate, ObservableList<String>> eventsByDate = new HashMap<>();

    @FXML
    public void initialize() {
        // Устанавливаем текущую дату
        LocalDate today = LocalDate.now();
        calendarDatePicker.setValue(today);
        loadEventsForDate(today);

        // Действие при выборе новой даты
        calendarDatePicker.setOnAction(event -> loadEventsForDate(calendarDatePicker.getValue()));
    }

    private void loadEventsForDate(LocalDate date) {
        // Загружаем события для выбранной даты
        ObservableList<String> events = eventsByDate.getOrDefault(date, FXCollections.observableArrayList());
        eventsListView.setItems(events);
    }

    @FXML
    private void addEvent() {
        LocalDate selectedDate = calendarDatePicker.getValue();
        if (selectedDate == null) {
            showAlert("Ошибка", "Выберите дату, чтобы добавить событие.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Добавить событие");
        dialog.setHeaderText("Введите название события");
        dialog.setContentText("Событие:");

        dialog.showAndWait().ifPresent(event -> {
            eventsByDate.computeIfAbsent(selectedDate, k -> FXCollections.observableArrayList()).add(event);
            loadEventsForDate(selectedDate);
        });
    }

    @FXML
    private void deleteEvent() {
        String selectedEvent = eventsListView.getSelectionModel().getSelectedItem();
        LocalDate selectedDate = calendarDatePicker.getValue();

        if (selectedEvent != null && selectedDate != null) {
            ObservableList<String> events = eventsByDate.get(selectedDate);
            if (events != null) {
                events.remove(selectedEvent);
                loadEventsForDate(selectedDate);
            }
        } else {
            showAlert("Ошибка", "Выберите событие для удаления.");
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
