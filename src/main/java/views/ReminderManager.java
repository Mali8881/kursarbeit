package views;

import java.time.LocalDateTime;

public class ReminderManager {
    private String content;
    private LocalDateTime dateTime;

    public ReminderManager(String content, LocalDateTime dateTime) {
        this.content = content;
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Напоминание: " + content + " | Время: " + dateTime;
    }
}
