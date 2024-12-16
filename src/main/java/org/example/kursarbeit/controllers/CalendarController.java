package org.example.kursarbeit.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.HashMap;

public class CalendarController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Text monthYearLabel;

    private YearMonth currentYearMonth = YearMonth.now();
    private final HashMap<LocalDate, String> tasks = new HashMap<>();

    // Метод для обновления отображаемого месяца
    @FXML
    private void onPreviousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        updateCalendar();
    }

    @FXML
    private void onNextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        updateCalendar();
    }

    // Метод для обновления календаря
    private void updateCalendar() {
        monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentYearMonth.getYear());

        // Очистка старых кнопок
        calendarGrid.getChildren().clear();

        // Создание нового календаря
        int row = 1;
        int col = 0;
        String[] daysOfWeek = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};

        // Добавление дней недели
        for (int i = 0; i < daysOfWeek.length; i++) {
            Text dayLabel = new Text(daysOfWeek[i]);
            calendarGrid.add(dayLabel, i, 0);
        }

        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1 = Monday
        int daysInMonth = currentYearMonth.lengthOfMonth();

        col = startDayOfWeek - 1;

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentYearMonth.atDay(day);

            Button dayButton = new Button(String.valueOf(day));
            dayButton.setPrefSize(50, 50);

            // Обработка клика по дню
            dayButton.setOnAction(e -> showTasksForDate(date));
            calendarGrid.add(dayButton, col, row);

            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    private void showTasksForDate(LocalDate date) {
        // Логика для отображения задач на выбранный день
    }
}
