package com.example.proyectogrupal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/proyectogrupal/views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void changeScene(String view, String text) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/proyectogrupal/views/"+view));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        myStage.setTitle(text);
        myStage.setScene(scene);
        myStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}