package org.example.kursarbeit.components;

public class TaskComponent {
    private String title;
    private String description;

    public TaskComponent(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Геттеры
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Сеттеры
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task: " + title + "\nDescription: " + description;
    }
}
