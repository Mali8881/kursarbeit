package Manager_models;

import javafx.beans.property.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Remainder {
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty description;
    private final ObjectProperty<LocalDateTime> reminderDate;
    private final BooleanProperty completed;

    // Конструктор для минимальных параметров
    public Remainder(int id, String title, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.reminderDate = new SimpleObjectProperty<>(LocalDateTime.now()); // Текущая дата по умолчанию
        this.completed = new SimpleBooleanProperty(false); // По умолчанию не выполнено
    }

    // Полный конструктор
    public Remainder(int id, String title, String description, LocalDateTime reminderDate, boolean completed) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.reminderDate = new SimpleObjectProperty<>(reminderDate);
        this.completed = new SimpleBooleanProperty(completed);
    }

    // Геттеры и сеттеры
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public LocalDateTime getReminderDate() {
        return reminderDate.get();
    }

    public ObjectProperty<LocalDateTime> reminderDateProperty() {
        return reminderDate;
    }

    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate.set(reminderDate);
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public void setCompleted(boolean value) {
        this.completed.set(value);
    }

    // Форматирование даты и времени
    public String getFormattedDate() {
        return reminderDate.get().toLocalDate().toString();
    }

    public String getFormattedTime() {
        return reminderDate.get().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s, Date: %s, Time: %s, Completed: %b",
                getId(), getTitle(), getFormattedDate(), getFormattedTime(), isCompleted());
    }
}
