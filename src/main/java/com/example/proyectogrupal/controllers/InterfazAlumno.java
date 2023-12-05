package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfazAlumno implements Initializable {
    @javafx.fxml.FXML
    private Button btnCerrar;
    @javafx.fxml.FXML
    private TableView tabla;
    @javafx.fxml.FXML
    private Button btnAñadir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void añadirActividad(ActionEvent actionEvent) {
        try {
            App.changeScene("AlumAñadeActi.fxml", "Página Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
