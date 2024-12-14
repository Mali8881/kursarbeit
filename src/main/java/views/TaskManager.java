package views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<TaskItem> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(TaskItem task) {
        tasks.add(task);
    }

    public List<TaskItem> getTasks() {
        return tasks;
    }

    public static class TaskItem {
        private int id;
        private String title;
        private String description;
        private LocalDate dueDate;
        private String status;

        public TaskItem(int id, String title, String description, LocalDate dueDate, String status) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.dueDate = dueDate;
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public String getStatus() {
            return status;
        }
    }
}
