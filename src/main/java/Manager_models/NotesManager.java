package Manager_models;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class NotesManager {
    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty content;
    private final ObjectProperty<LocalDateTime> createdAt;

    public NotesManager(int id, String title, String content, LocalDateTime createdAt) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
    }

    // Геттеры и сеттеры для свойств
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public StringProperty contentProperty() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }
}
