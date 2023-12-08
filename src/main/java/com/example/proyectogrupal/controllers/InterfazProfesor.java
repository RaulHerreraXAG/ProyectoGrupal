package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.Alumno;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;

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
    private Button RegistarEmpresa;
    @javafx.fxml.FXML
    private TableView<Alumno> TvAlumnos;

    ObservableList<Alumno> observableList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Button btncerrarsesion;
    @javafx.fxml.FXML
    private Label labelProfesor;
    @FXML
    private Button TablaEmpresa;


    @javafx.fxml.FXML
    public void RegistrarA(ActionEvent actionEvent) throws IOException {
        App.changeScene("registrar-alumno.fxml","Registrar Alumno");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        labelProfesor.setText("Bienvenido " + Session.getCurrentProfesor().getNombre());


        cNombre.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getNombre());
        });
        cApellidos.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getApellidos());
        });
        cEmail.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getEmail());
        });
        cTTelefono.setCellValueFactory((fila) -> {
            String telefono= String.valueOf(fila.getValue().getTelefono());
            return new SimpleStringProperty(telefono);
        });
        observableList.addAll(Session.getCurrentProfesor().getAlumnos());
        TvAlumnos.setItems(observableList);

        TvAlumnos.setOnMouseClicked(event -> {
            if(event.getClickCount()==1){
                Alumno alumnoselect = TvAlumnos.getSelectionModel().getSelectedItem();
                if (alumnoselect != null){
                    Session.setCurrentAlumno(alumnoselect);
                    try {
                        App.changeScene("DatosYEditarAlumno.fxml", "Editar Alumno");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void actualizarTablaAlumnos() {
        TvAlumnos.getItems().clear();
        TvAlumnos.getItems().addAll(Session.getCurrentProfesor().getAlumnos());
    }




    @javafx.fxml.FXML
    public void RegistrarEmp(ActionEvent actionEvent) throws IOException {
        App.changeScene("registrar-empresas.fxml","Registrar Empresa");

    }

    @javafx.fxml.FXML
    public void cerrarsesion(ActionEvent actionEvent) {
        try {
            App.changeScene("login.fxml","Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void MenuEmpresa(ActionEvent actionEvent) throws IOException {
        App.changeScene("InformacionEmpresa.fxml","Tus Empresas");
    }
}