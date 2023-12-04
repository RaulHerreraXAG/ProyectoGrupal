package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.alumno.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfazProfesor implements Initializable
{
    @javafx.fxml.FXML
    private TableColumn<Alumno , String>  cNombre;
    @javafx.fxml.FXML
    private TableColumn<Alumno , String> cApellidos;
    @javafx.fxml.FXML
    private TableColumn<Alumno , String>  cEmail;
    @javafx.fxml.FXML
    private TableColumn<Alumno , String>  cTTelefono;
    @javafx.fxml.FXML
    private Button RegistraAlumnos;
    @javafx.fxml.FXML
    private Button TablaAlumnos;
    @javafx.fxml.FXML
    private Button RegistarEmpresa;
    @javafx.fxml.FXML
    private TableView TvAlumnos;


    @javafx.fxml.FXML
    public void RegistrarA(ActionEvent actionEvent) throws IOException {
        App.changeScene("registrar-alumno.fxml","Registrar Alumno");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void MenuTablaAlumno(ActionEvent actionEvent) throws IOException {
        App.changeScene("InformacionDeAlumnos.fxml","Tus Alumnos");
    }

    @javafx.fxml.FXML
    public void RegistrarEmp(ActionEvent actionEvent) throws IOException {
        App.changeScene("registrar-empresa.fxml","Registrar Empresa");
    }
}