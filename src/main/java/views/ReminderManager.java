package views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReminderManager {
    private List<Reminder> reminders;

    public ReminderManager() {
        reminders = new ArrayList<>();
    }

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void openRemindersSection(Stage parentStage) {
        Stage reminderStage = new Stage();
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20;");
        layout.getChildren().add(new Label("Раздел напоминаний пока пуст"));

        Scene scene = new Scene(layout, 300, 200);
        reminderStage.setTitle("Напоминания");
        reminderStage.setScene(scene);
        reminderStage.initOwner(parentStage);
        reminderStage.show();
    }

    public static class Reminder {
        private int id;
        private String title;
        private LocalDate date;

        public Reminder(int id, String title, LocalDate date) {
            this.id = id;
            this.title = title;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public LocalDate getDate() {
            return date;
        }
    }
}
