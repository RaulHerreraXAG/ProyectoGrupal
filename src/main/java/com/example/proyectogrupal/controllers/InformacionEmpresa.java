package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.empresa.Empresa;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class InformacionEmpresa {

    @javafx.fxml.FXML
    private TableColumn<Empresa , String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<Empresa , String> cEmail;
    @javafx.fxml.FXML
    private TableColumn<Empresa , String> cTelefono;
    @javafx.fxml.FXML
    private TableColumn<Empresa , String> cResponsable;
    @javafx.fxml.FXML
    private TableColumn<Empresa , String> cObservaciones;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private TableView<Empresa> tvEmpresa;

    ObservableList<Empresa> observableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        cNombre.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getNombre());
        });
        cResponsable.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getResponsable());
        });
        cEmail.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getEmail());
        });
        cTelefono.setCellValueFactory((fila) -> {
            String telefono= String.valueOf(fila.getValue().getTelefono());
            return new SimpleStringProperty(telefono);
        });
        cObservaciones.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getObservaciones());
        });

        observableList.addAll(Session.getCurrentEmpresa());
        tvEmpresa.setItems(observableList);

        tvEmpresa.setOnMouseClicked(event -> {
            if(event.getClickCount()==1){
                Empresa empresaselect = tvEmpresa.getSelectionModel().getSelectedItem();
                if (empresaselect != null){
                    Session.setCurrentEmpresa(empresaselect);
                    try {
                        App.changeScene("EditarEmpresa.fxml", "Editar Empresa");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) throws IOException {
        App.changeScene("PaginaProfesor.fxml","Inicio Profesor");
    }
}