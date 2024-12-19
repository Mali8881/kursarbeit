package Manager_models ;


import javafx.beans.binding.BooleanExpression;

import java.time.LocalDate;


import javafx.beans.property.*;
import java.time.LocalDate;

public class TaskManager {
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty category;
    private final ObjectProperty<LocalDate> startDate;
    private final ObjectProperty<LocalDate> dueDate;
    private final StringProperty priority;
    private final StringProperty status;

    // Конструктор
    public TaskManager(int id, String title, String description, String category,
                       LocalDate startDate, LocalDate dueDate, String priority, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.startDate = new SimpleObjectProperty<>(startDate);
        this.dueDate = new SimpleObjectProperty<>(dueDate);
        this.priority = new SimpleStringProperty(priority);
        this.status = new SimpleStringProperty(status);
    }

    // Геттеры и сеттеры для IntegerProperty id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Геттеры и сеттеры для StringProperty title
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    // Геттеры и сеттеры для StringProperty description
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    // Геттеры и сеттеры для StringProperty category
    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    // Геттеры и сеттеры для ObjectProperty startDate
    public LocalDate getStartDate() {
        return startDate.get();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    // Геттеры и сеттеры для ObjectProperty dueDate
    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    // Геттеры и сеттеры для StringProperty priority
    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    // Геттеры и сеттеры для StringProperty status
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
