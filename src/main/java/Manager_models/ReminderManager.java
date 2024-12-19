package Manager_models;

import java.time.LocalDateTime;

public class ReminderManager {
    private int id;
    private String content;
    private LocalDateTime dateTime;

    public ReminderManager(int id, String content, LocalDateTime dateTime) {
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return content + " | Время: " + dateTime.toString();
    }
}
