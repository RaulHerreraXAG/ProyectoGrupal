package com.example.proyectogrupal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class InterfazProfesor implements Initializable
{
    @javafx.fxml.FXML
    private MenuItem AlumnoTabla;
    @javafx.fxml.FXML
    private MenuItem RegistrarAlumno;
    @javafx.fxml.FXML
    private MenuItem RegistrarEmpresa;
    @javafx.fxml.FXML
    private MenuItem Salr;
    @javafx.fxml.FXML
    private TableColumn cNombre;
    @javafx.fxml.FXML
    private TableColumn cApellidos;
    @javafx.fxml.FXML
    private TableColumn cEmail;
    @javafx.fxml.FXML
    private TableColumn cTTelefono;


    @javafx.fxml.FXML
    public void TablaAlumno(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void RegistrarA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void RegistrarE(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}