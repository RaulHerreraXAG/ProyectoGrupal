package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.actividad.Tips;
import com.example.proyectogrupal.alumno.Alumno;
import com.example.proyectogrupal.alumno.AlumnoDAO;
import javafx.beans.Observable;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class InterfazAlumno implements Initializable {
    @javafx.fxml.FXML
    private Button btnCerrar;
    @javafx.fxml.FXML
    private Button btnAñadir;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cTipoPractica;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cHorasDia;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cActividadRealizada;
    @javafx.fxml.FXML
    private TableColumn<Actividad, String> cObservaciones;
    private ObservableList<Actividad> observableList;

    private ActividadDAO actividadDAO = new ActividadDAO();
    @javafx.fxml.FXML
    private TableView TvActividades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Mostramos los datos en las columnas.
        cFecha.setCellValueFactory((fila) -> {
            Date fecha = fila.getValue().getFecha();
            String fechaformato= "";

            if(fecha != null){
                SimpleDateFormat nuevafecha = new SimpleDateFormat("dd-MM-yyyy");
                fechaformato = nuevafecha.format(fecha);
            }
            return new SimpleStringProperty(fechaformato);
        });

        cTipoPractica.setCellValueFactory((fila) -> {
            String tipo = String.valueOf(fila.getValue().getTipo());
            return new SimpleStringProperty(tipo);
        });

        cHorasDia.setCellValueFactory((fila) -> {
            String horas = String.valueOf(fila.getValue().getHoras());
            return new SimpleStringProperty(horas);
        });

        cActividadRealizada.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getActividad());
        });

        cObservaciones.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getObservaciones());
        });

        //cargamos los datos en la tabla
        observableList = FXCollections.observableArrayList();
        Session.setCurrentAlumno(new AlumnoDAO().get(Session.getCurrentAlumno().getID_Alumno()));
        observableList.setAll(Session.getCurrentAlumno().getActividad_diaria());
        TvActividades.setItems(observableList);

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
