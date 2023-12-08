package com.example.proyectogrupal.controllers;

import com.example.proyectogrupal.App;
import com.example.proyectogrupal.Session;
import com.example.proyectogrupal.actividad.Actividad;
import com.example.proyectogrupal.actividad.ActividadDAO;
import com.example.proyectogrupal.actividad.Tips;
import com.example.proyectogrupal.alumno.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AnadirActividadDeAlumno implements Initializable {
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
    private Label labelNombre;
    @javafx.fxml.FXML
    private Button btnAñadir;
    @javafx.fxml.FXML
    private Button btnCancelar;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ActividadDAO actividadDAO = new ActividadDAO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelNombre.setText("Bienvenido/a " + Session.getCurrentAlumno().getNombre());
        spinnerHoras.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, 1, 1));

        rbDual.setToggleGroup(toggleGroup);
        rbFCT.setToggleGroup(toggleGroup);

    }


    @javafx.fxml.FXML
    public void btnAnadir(ActionEvent actionEvent) throws IOException {
        Alumno alumnoAsociado = Session.getCurrentAlumno();
        Actividad actividad = new Actividad();
        actividad.setAlumno(alumnoAsociado);

        if (txtActividad.getText().length() > 1){
            actividad.setActividad(txtActividad.getText());
        }
        if (txtObservaciones.getText().length() > 1){
            actividad.setObservaciones(txtObservaciones.getText());
        }

        actividad.setHoras((Integer) spinnerHoras.getValue());

        //obtener la fecha seleccionada
        LocalDate fechaseleccionada = DatePickerFecha.getValue();

        if (fechaseleccionada != null) {
            LocalDate fechaSQL = (fechaseleccionada);
            actividad.setFecha(fechaSQL);
        } else {
            System.out.println("Error: Debes seleccionar una fecha.");
            return;
        }


        // obtener el tipo de practica.
        RadioButton seleccion = (RadioButton) toggleGroup.getSelectedToggle();
        if (seleccion != null) {
            if (seleccion == rbDual) {
                actividad.setTipo(Tips.DUAL);
            } else if (seleccion == rbFCT) {
                actividad.setTipo(Tips.FCT);
            }
        }
        actividadDAO.save(actividad);
        App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        try {
            App.changeScene("PaginaAlumno.fxml", "Pagina Alumno");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}