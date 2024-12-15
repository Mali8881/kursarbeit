package views;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class Task {

    private final StringProperty title;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> dueDate;
    private final StringProperty priority;

    public Task(String title, String description, LocalDate dueDate, String priority) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.priority = new SimpleStringProperty(priority);
    }

    // Getters for the properties
    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    // Getters for the actual values (if needed)
    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public String getPriority() {
        return priority.get();
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title.get() + '\'' +
                ", description='" + description.get() + '\'' +
                ", dueDate=" + dueDate.get() +
                ", priority='" + priority.get() + '\'' +
                '}';
    }
}
