package com.example.proyectogrupal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/proyectogrupal/views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


    public static void changeScene(String fxml,String title) throws IOException {
       try{
           FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/proyectogrupal/views/"+fxml));
           Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
           stage.setTitle(title);
           stage.setScene(scene);
           stage.show();
       }catch (IOException e){
           System.out.println("Error al cargar el fxml.");
           throw new RuntimeException(e);
       }

    }


    public static void main(String[] args) {
        launch();
    }
}