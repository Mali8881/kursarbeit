package views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.example.kursarbeit.controllers.CalendarController;

public class Calendar {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 400;
    private static final int PADDING = 20;
    private static final int BUTTON_GAP = 10;

    private CalendarController controller = new CalendarController();
    private YearMonth currentMonth = YearMonth.now();

    public void show(Stage primaryStage) {
        // Заголовок страницы
        Text pageTitle = new Text("Календарь");
        pageTitle.setFont(Font.font("Arial", 20));

        // Сетка календаря
        GridPane calendarGrid = createCalendarGrid();

        // Переключатели месяца
        HBox monthNavigation = createMonthNavigation(calendarGrid);

        // Общий макет
        VBox layout = new VBox(PADDING, pageTitle, monthNavigation, calendarGrid);
        layout.setStyle("-fx-padding: " + PADDING + ";");

        Scene scene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Календарь");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createCalendarGrid() {
        GridPane calendarGrid = new GridPane();
        calendarGrid.setHgap(BUTTON_GAP);
        calendarGrid.setVgap(BUTTON_GAP);

        Map<LocalDate, List<String>> reminders = controller.loadReminders();
        List<LocalDate> daysInMonth = getDaysInMonth(currentMonth);
        int dayOfWeekStart = currentMonth.atDay(1).getDayOfWeek().getValue() % 7;

        for (int i = 0; i < daysInMonth.size(); i++) {
            LocalDate day = daysInMonth.get(i);
            Button dayButton = new Button(String.valueOf(day.getDayOfMonth()));

            if (reminders.containsKey(day)) {
                dayButton.setStyle("-fx-background-color: #FFEB3B;");
                Tooltip tooltip = new Tooltip(String.join(", ", reminders.get(day)));
                Tooltip.install(dayButton, tooltip);
            }

            dayButton.setOnAction(e -> showDayDetails(day, reminders.get(day)));
            calendarGrid.add(dayButton, (i + dayOfWeekStart) % 7, (i + dayOfWeekStart) / 7);
        }
        return calendarGrid;
    }

    private HBox createMonthNavigation(GridPane calendarGrid) {
        Button prevMonthButton = new Button("<");
        prevMonthButton.setOnAction(e -> {
            currentMonth = currentMonth.minusMonths(1);
            updateCalendarGrid(calendarGrid);
        });

        Button nextMonthButton = new Button(">");
        nextMonthButton.setOnAction(e -> {
            currentMonth = currentMonth.plusMonths(1);
            updateCalendarGrid(calendarGrid);
        });

        Label monthLabel = new Label(currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        monthLabel.setFont(Font.font("Arial", 16));

        HBox navigation = new HBox(10, prevMonthButton, monthLabel, nextMonthButton);
        navigation.setStyle("-fx-alignment: center;");
        return navigation;
    }

    private void updateCalendarGrid(GridPane calendarGrid) {
        calendarGrid.getChildren().clear();
        createCalendarGrid().getChildren().forEach(calendarGrid.getChildren()::add);
    }

    private void showDayDetails(LocalDate date, List<String> tasks) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("День: " + date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        alert.setHeaderText("Задачи на " + date);
        alert.setContentText(tasks == null || tasks.isEmpty() ? "Нет задач" : String.join("\n", tasks));
        alert.showAndWait();
    }

    private List<LocalDate> getDaysInMonth(YearMonth yearMonth) {
        List<LocalDate> days = new ArrayList<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            days.add(yearMonth.atDay(i));
        }
        return days;
    }
}
