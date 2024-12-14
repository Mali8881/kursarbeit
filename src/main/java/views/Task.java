package views;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Task {
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> deadline;
    private final StringProperty status;

    // Конструктор с параметрами
    public Task(int id, String title, String description, LocalDate deadline, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.deadline = new SimpleObjectProperty<>(deadline);
        this.status = new SimpleStringProperty(status);
    }

    // Конструктор по умолчанию
    public Task() {
        this(0, "Новая задача", "Описание отсутствует", LocalDate.now(), "Не начато");
    }

    // Геттеры и сеттеры для свойств
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }

    public StringProperty statusProperty() {
        return status;
    }

    // Для удобного отображения
    @Override
    public String toString() {
        return title.get() + " (" + status.get() + ")";
    }
}
