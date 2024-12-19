package org.example.kursarbeit.components;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class MenuComponent extends Pane {

    public MenuComponent() {
        // Настройка меню
        setStyle("-fx-background-color: #2c3e50; -fx-padding: 20px;");
        setPrefSize(200, 600);

        Button taskButton = new Button("Tasks");
        taskButton.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;");
        taskButton.setPrefSize(160, 40);

        // Добавление кнопки на панель
        getChildren().add(taskButton);
    }
}
