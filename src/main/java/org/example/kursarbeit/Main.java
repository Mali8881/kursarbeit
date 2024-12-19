package org.example.kursarbeit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.kursarbeit.components.BurgerMenu;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Главный макет
        BorderPane layout = new BorderPane();

        // Подключение бургер-меню
        BurgerMenu burgerMenu = new BurgerMenu();
        layout.setLeft(burgerMenu.getBurgerContainer()); // Добавляем бургер-меню слева
        layout.setTop(burgerMenu.getBurgerButton()); // Добавляем бургер-кнопку сверху

        // Центр: Анимированный текст "Task-Kage"
        StackPane centerPane = new StackPane();
        Label animatedText = new Label("Task-Kage");
        animatedText.getStyleClass().add("animated-text");
        centerPane.getChildren().add(animatedText);
        layout.setCenter(centerPane);

        // Анимация текста: плавное движение по оси Y
        Timeline textAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(animatedText.translateYProperty(), -20)),
                new KeyFrame(Duration.seconds(2), new KeyValue(animatedText.translateYProperty(), 20))
        );
        textAnimation.setAutoReverse(true);
        textAnimation.setCycleCount(Timeline.INDEFINITE);
        textAnimation.play();

        // Настройка сцены с фоновым изображением
        Scene scene = new Scene(layout, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/org/example/kursarbeit/background.css").toExternalForm());

        primaryStage.setTitle("Task-Kage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
