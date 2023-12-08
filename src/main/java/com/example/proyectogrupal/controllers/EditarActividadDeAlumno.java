package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Tips;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class EditarActividadDeAlumno implements Initializable {

    @javafx.fxml.FXML
    private DatePicker DatePickerFecha;
    @javafx.fxml.FXML
    private Spinner spinnerHoras;
    @javafx.fxml.FXML
    private RadioButton rbDual;
    @javafx.fxml.FXML
    private RadioButton rbFCT;
    @javafx.fxml.FXML
    private TextField txtActividad;
    @javafx.fxml.FXML
    private TextField txtObservaciones;
    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private Label labelNombre;
    @javafx.fxml.FXML
    private Button btnActualizar;

    private final ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelNombre.setText("Bienvenido/a " + Session.getCurrentAlumno().getNombre());
        txtActividad.setText(Session.getCurrentActividad().getActividad());
        txtObservaciones.setText(Session.getCurrentActividad().getObservaciones());

        spinnerHoras.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Math.toIntExact(Session.getCurrentActividad().getHoras()), 8,  1));

        //Esto es para poder pasar un DataPicker a otro sino no deja pasar los datos
        LocalDate actfecha = Session.getCurrentActividad().getFecha();
        DatePickerFecha.setValue(actfecha);

        rbDual.setToggleGroup(toggleGroup);
        rbFCT.setToggleGroup(toggleGroup);




    }



    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        try {
            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @javafx.fxml.FXML
    public void btnActualizar(ActionEvent actionEvent) {

    }
}
