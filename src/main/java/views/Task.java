package views;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Task {
    private final StringProperty id;  // Строковый идентификатор
    private final IntegerProperty taskId;  // Идентификатор задачи
    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty category;
    private final ObjectProperty<LocalDate> startDate;  // Дата начала
    private final ObjectProperty<LocalDate> dueDate;  // Дата выполнения
    private final StringProperty priority;
    private final StringProperty status;

    // Конструктор с id как String и startDate
    public Task(String id, String title, String description, String category, LocalDate startDate, LocalDate dueDate, String priority, String status) {
        this.id = new SimpleStringProperty(id);  // Идентификатор как String
        this.taskId = new SimpleIntegerProperty(Integer.parseInt(id));  // Идентификатор задачи как int
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.startDate = new SimpleObjectProperty<>(startDate);  // Дата начала
        this.dueDate = new SimpleObjectProperty<>(dueDate);  // Дата выполнения
        this.priority = new SimpleStringProperty(priority);
        this.status = new SimpleStringProperty(status);
    }

    // Методы для получения свойств
    public StringProperty idProperty() { return id; }
    public IntegerProperty taskIdProperty() { return taskId; }
    public StringProperty titleProperty() { return title; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty categoryProperty() { return category; }
    public ObjectProperty<LocalDate> startDateProperty() { return startDate; }
    public ObjectProperty<LocalDate> dueDateProperty() { return dueDate; }
    public StringProperty priorityProperty() { return priority; }
    public StringProperty statusProperty() { return status; }

    // Методы для изменения значений
    public void setId(String id) { this.id.set(id); }
    public void setTaskId(int taskId) { this.taskId.set(taskId); }
    public void setTitle(String title) { this.title.set(title); }
    public void setDescription(String description) { this.description.set(description); }
    public void setCategory(String category) { this.category.set(category); }
    public void setStartDate(LocalDate startDate) { this.startDate.set(startDate); }
    public void setDueDate(LocalDate dueDate) { this.dueDate.set(dueDate); }
    public void setPriority(String priority) { this.priority.set(priority); }
    public void setStatus(String status) { this.status.set(status); }
}
