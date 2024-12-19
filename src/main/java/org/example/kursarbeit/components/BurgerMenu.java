package org.example.kursarbeit.components;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;

public class BurgerMenu {

    private VBox burgerContainer;
    private Button burgerButton;
    private boolean menuVisible = false; // Флаг для отслеживания видимости меню

    public BurgerMenu() {
        // Главный контейнер
        burgerContainer = new VBox(10);
        burgerContainer.setPadding(new Insets(20));
        burgerContainer.setAlignment(Pos.TOP_LEFT);
        burgerContainer.setStyle("-fx-background-color: #2c2f36;");
        burgerContainer.setPrefWidth(200);

        // Создание кнопки-бургера
        burgerButton = createBurgerButton();

        // Анимация меню
        TranslateTransition toggleMenuAnimation = new TranslateTransition(Duration.millis(300), burgerContainer);
        toggleMenuAnimation.setFromX(-200); // Исходное положение вне экрана
        toggleMenuAnimation.setToX(0);

        // Кнопка-бургер (логика скрытия/показа меню)
        burgerButton.setOnAction(e -> {
            if (menuVisible) {
                toggleMenuAnimation.setRate(-1); // Обратная анимация для скрытия
                menuVisible = false;
            } else {
                toggleMenuAnimation.setRate(1); // Анимация появления
                menuVisible = true;
            }
            toggleMenuAnimation.play();
        });

        // Создание разделов меню
        Button tasksButton = createMenuButton("Задачи");
        Button contactsButton = createMenuButton("Контакты");
        Button notesButton = createMenuButton("Заметки");
        Button remindersButton = createMenuButton("Напоминания");
        Button profileButton = createMenuButton("Профиль");
        Button homeButton = createMenuButton("На главную");

        burgerContainer.getChildren().addAll(
                tasksButton, contactsButton, notesButton,
                remindersButton, profileButton, homeButton
        );

        // Изначально скрываем меню
        burgerContainer.setTranslateX(-200);
    }

    // Метод для создания кнопки "бургер"
    private Button createBurgerButton() {
        Button burger = new Button();
        burger.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        burger.setPrefSize(40, 40);

        // Создаём символ "бургер" из прямоугольников
        StackPane burgerIcon = new StackPane();
        burgerIcon.setAlignment(Pos.CENTER);
        HBox lines = new HBox(5); // Контейнер для линий

        for (int i = 0; i < 3; i++) {
            Rectangle line = new Rectangle(30, 3, Color.WHITE);
            lines.getChildren().add(line);
        }

        burgerIcon.getChildren().add(lines);
        burger.setGraphic(burgerIcon); // Устанавливаем графику на кнопку

        return burger;
    }

    // Метод для создания кнопок меню
    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("burger-button");
        button.setPrefWidth(160);
        return button;
    }

    public VBox getBurgerContainer() {
        return burgerContainer;
    }

    public Button getBurgerButton() {
        return burgerButton;
    }
}
