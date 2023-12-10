package com.example.proyectogrupal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal que inicia la aplicación y gestiona los cambios de escena en JavaFX.
 */
public class App extends Application {
    private static Stage stage;

    /**
     * Inicia la aplicación y muestra la escena de inicio de sesión.
     *
     * @param stage Escenario principal de la aplicación.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/proyectogrupal/views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Cambia la escena actual por una nueva escena especificada por un archivo FXML.
     *
     * @param fxml  Nombre del archivo FXML que representa la nueva escena.
     * @param title Título de la nueva escena.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    public static void changeScene(String fxml, String title) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/proyectogrupal/views/" + fxml));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el fxml.");
            throw new RuntimeException(e);
        }

    }


    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param args Argumentos pasados al iniciar la aplicación.
     */
    public static void main(String[] args) {
        launch();
    }
}