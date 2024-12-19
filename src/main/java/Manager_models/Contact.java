package Manager_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Contact {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;
    private final SimpleStringProperty group; // Добавлено поле для группировки
    private SimpleStringProperty createdAt = null; // Храним дату как строку

    // Форматтер для преобразования LocalDateTime <-> String
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Конструктор


    public Contact(int id, String name, String phone, String email, String group) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.group = new SimpleStringProperty(group);
        this.createdAt = new SimpleStringProperty(null);
    }

    // Геттеры и сеттеры
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getGroup() {
        return group.get();
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public LocalDateTime getCreatedAt() {
        String dateString = createdAt.get();
        return dateString != null ? LocalDateTime.parse(dateString, formatter) : null;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt != null ? createdAt.format(formatter) : null);
    }

    public SimpleStringProperty createdAtProperty() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id.get() +
                ", name='" + name.get() + '\'' +
                ", phone='" + phone.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", group='" + group.get() + '\'' +
                ", createdAt=" + createdAt.get() +
                '}';
    }
}
